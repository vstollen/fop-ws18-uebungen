;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V7) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
;; Type: (list of number) (list of number) -> (list of (list of number))
;; Returns: The cartesian product of two lists
(define (cartesian-prod lst-x lst-y)
  ;; Type: number -> (list of number)
  ;; Returns: Cartesian product given a number and a list
  (foldr append empty (map (lambda (x)
         ;; Type: number -> (list of number)
         ;; Returns: list of y with x
         (map (lambda (y)
                (list x y)) lst-y))
       lst-x)))

(check-expect (cartesian-prod (list 1 2) (list 3 4)) (list (list 1 3) (list 1 4) (list 2 3) (list 2 4)))
(check-expect (cartesian-prod (list 1 2 3) (list 4 5 6)) (list (list 1 4) (list 1 5) (list 1 6) (list 2 4) (list 2 5) (list 2 6) (list 3 4) (list 3 5) (list 3 6)))
(check-expect (cartesian-prod empty empty) empty)
(check-expect (cartesian-prod empty (list 1 2)) empty)
(check-expect (cartesian-prod (list 1 2) empty) empty)
(check-expect (cartesian-prod (list 1 2 3) (list 1 2)) (list (list 1 1) (list 1 2) (list 2 1) (list 2 2) (list 3 1) (list 3 2)))
(check-expect (cartesian-prod (list 1 2) (list 1 2 3)) (list (list 1 1) (list 1 2) (list 1 3) (list 2 1) (list 2 2) (list 2 3)))