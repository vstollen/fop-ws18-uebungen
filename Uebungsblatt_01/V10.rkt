;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V10) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
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
;; Returns: If the list contains duplicate entries
(define (duplicates? lst)
  (if (empty? lst)
      false
      (if (contains-x? (rest lst) (first lst))
          true
          (duplicates? (rest lst)))))

(check-expect (duplicates? empty) false)
(check-expect (duplicates? (list 1 2 3 4 5)) false)
(check-expect (duplicates? (list 1 3 3 4 5)) true)

;; Type: (list of number or (list of number or (list of num...))) (list of number) -> (list of number)
;; Returns: Flattened list of numbers
(define (collect lst oracle)
  (if (empty? lst)
      oracle
      (collect (rest lst) (append oracle (if (number? (first lst))
                                             (list (first lst))
                                             (collect (first lst) empty))))))

(check-expect (collect (list 1 3 (list 2 5) 5 (list 98)) empty) (list 1 3 2 5 5 98))
(check-expect (collect empty empty) empty)
(check-expect (collect (list 1 2 3) empty) (list 1 2 3))
(check-expect (collect (list (list (list 0))) empty) (list 0))

;; Type: (list of number or (list of number or (list of num...))) -> boolean
;; Returns: If a number can be found multiple times in the list or one of it's nested lists
(define (duplicates-deep? deep-lst)
  (duplicates? (collect deep-lst empty)))

(check-expect (duplicates-deep? (list 1 1)) true)
(check-expect (duplicates-deep? empty) false)
(check-expect (duplicates-deep? (list 1 2 3 4 (list 5 6 7 8) 6)) true)