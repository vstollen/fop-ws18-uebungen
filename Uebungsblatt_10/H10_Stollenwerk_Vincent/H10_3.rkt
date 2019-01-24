;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H10_3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct queen (x y))

;; Type: queen (list of queen) -> boolean
;; Returns: true if a queen can be attacked by a list of other queens
;; example: (under-attack (make-queen 1 1) (list (make-queen 1 2))) is true
(define (under-attack new-queen queens)
  (cond
    [(empty? queens) false]
    [else
     (or
      ;; queens on same row
      (= (queen-x new-queen) (queen-x (first queens)))
      ;; queens on same col
      (= (queen-y new-queen) (queen-y (first queens)))
      ;; queens on same diagonal
      (= (abs (- (queen-x new-queen) (queen-x (first queens))))
         (abs (- (queen-y new-queen) (queen-y (first queens)))))
      ;; next queen
      (under-attack new-queen (rest queens)))]))


;; Type: number -> number
;; Returns: the number off solutions of the n-queens problem for board size nxn
(define (n-queens n) (/ (queens-total n n empty 0 0) (factorial n)))

;; n-queens student tests
(check-expect (n-queens 2) 0)
(check-expect (n-queens 7) 40)
(check-expect (n-queens 5) 10)

;; Type: natural natural (list of queen) natural natural -> natural
;; Returns: the number of solutions to put the left queens on the board of size field-size*field-size
;;          different queens at the same position are counted as multiple solutions
(define (queens-total field-size queens-left queens x y)
  (cond
    [(= queens-left 0) 1]
    [(>= x field-size) (queens-total field-size queens-left queens 0 (+ y 1))]
    [(>= y field-size) 0]
    [else (+ (queens-total field-size queens-left queens (+ x 1) y)
             (if (under-attack (make-queen x y) queens)
                 0
                 (queens-total field-size (- queens-left 1) (cons (make-queen x y) queens) 0 0)))]))

;; queens-total student tests
(check-expect (queens-total 3 3 empty 0 0) 0)
(check-expect (queens-total 4 4 empty 0 0) 48)
(check-expect (queens-total 7 7 empty 0 0) 201600)

;; Type: natural -> natural
;; Returns: factorial of n
(define (factorial n)
  (if (= n 0)
      1
      (* n (factorial (- n 1)))))

;; factorial student tests
(check-expect (factorial 0) 1)
(check-expect (factorial 14) 87178291200)
(check-expect (factorial 9) 362880)