;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H10_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct node (name neighbors))

(define A (make-node "A" (list "B" "C")) )
(define B (make-node "B" (list "E")) )
(define C (make-node "C" (list "B" "D" "E")) )
(define D (make-node "D" (list "F")) )
(define E (make-node "E" (list "F")) )
(define F (make-node "F" (list "G")) )
(define G (make-node "G" empty) )

(define-struct graph (name nodes))

(define G1 (make-graph "G1" (list A B C D E F G)))

;; Type: node node graph -> (list of String)
;; Returns: a path from origin to dest in the given graph
;; Precondition: cycle-free graph
(define (find-route origin dest graph) 
  (if (equal? origin dest)
      (list (node-name dest))
      (cond
        [(empty? (node-neighbors origin)) false]
        ;; Type: (list of String) -> list of String
        ;; Returns: The given rout after adding the origin to the front, if rout is false tries another path
        [else ((lambda (route)
                (if (equal? false route)
                    (find-route (make-node (node-name origin) (rest (node-neighbors origin))))
                    (cons (node-name origin) route)))
               ;; Type: node -> boolean
               ;; Returns: All graph notes matching the name of the first neighbor of origin
              (find-route (first (filter (lambda (node)
                                    (if (equal? (node-name node) (first (node-neighbors origin)))
                                        true
                                        false))
                                  (graph-nodes graph)))
                          dest
                          graph))])))

;; Student tests
(define A-S (make-node "A-S" (list "B-S" "C-S")))
(define B-S (make-node "B-S" (list "E-S")))
(define C-S (make-node "C-S" empty))
(define D-S (make-node "D-S" (list "C-S")))
(define E-S (make-node "E-S" (list "D-S")))
(define F-S (make-node "F-S" (list "E-S")))

(define G2 (make-graph "G2" (list A-S B-S C-S D-S E-S F-S)))

(check-expect (find-route A A G1) (list "A"))
(check-expect (find-route F-S C-S G2) (list "F-S" "E-S" "D-S" "C-S"))
(check-expect (member (find-route A-S C-S G2) (list
                                            (list "A-S" "C-S")
                                            (list "A-S" "B-S" "E-S" "D-S" "C-S")))
              true)

;; Tests
(check-expect (find-route B F G1) (list "B" "E" "F"))
(check-expect (find-route G A G1) false)
(check-expect (member (find-route A G G1) (list
                                           (list "A" "B" "E" "F" "G")
                                           (list "A" "C" "E" "F" "G")
                                           (list "A" "C" "D" "F" "G")
                                           (list "A" "C" "B" "E" "F" "G")
                                           ))
              
              true)
