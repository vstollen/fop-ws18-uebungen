;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: (list of ANY) (list of ANY) -> (list of (list of ANY))
;; Precondition: Both lists have the same length
;; Returns: List of ordered pairs
(define (zip lst-a lst-b)
  (map list lst-a lst-b))

(check-expect (zip (list "a" "b") (list 1 2)) (list (list "a" 1) (list "b" 2)))
(check-expect (zip empty empty) empty)
(check-expect (zip (list "Hans" "Greta" "Gundula" "Herrmann")
                   (list "Mueller" "Mayer" "Schmied" "Reiners"))
              (list (list "Hans" "Mueller") (list "Greta" "Mayer") (list "Gundula" "Schmied") (list "Herrmann" "Reiners")))


;; Type: (list of number) (list of number) -> number
;; Precondition: Both lists have the same length
;; Returns: Scalar of two vectors
(define (vec-mult lst-a lst-b)
  (foldr + 0 (map * lst-a lst-b)))

(check-expect (vec-mult (list 1 2 3) (list 4 5 6)) 32)
(check-expect (vec-mult (list 1 0 1) (list 0 1 0)) 0)
(check-expect (vec-mult (list -5 2 1) (list 3 -8 5)) -26)