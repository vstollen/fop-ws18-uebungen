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

;; TODO
;; Type: node node graph -> (list of String)
;; Returns: a path from origin to dest in the given graph
;; Precondition: cycle-free graph
(define (find-route origin dest graph) 
  (if (member (graph-nodes graph) dest)
      true
      )

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
