;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V9) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: (list of number) number -> boolean
;; Returns: If the list contains the number x
(define (contains-x? number-list x)
  (if (empty? number-list)
      false
      (if (= x (first number-list))
          true
          (contains-x? (rest number-list) x))))

(check-expect (contains-x? (list 1 2 3 4 5) 5) true)
(check-expect (contains-x? empty 8) false)
(check-expect (contains-x? (list 1 7 38) 42) false)

;; Type: (list of number) -> boolean
;; 
(define (duplicates? lst)
  (if (empty? lst)
      false
      (if (contains-x? (rest lst) (first lst))
          true
          (duplicates? (rest lst)))))

(check-expect (duplicates? empty) false)
(check-expect (duplicates? (list 1 2 3 4 5)) false)
(check-expect (duplicates? (list 1 3 3 4 5)) true)