;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V11) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct person (age sex))
(define-struct student (person id))

;; Type: (list of number) -> number
;; Returns: Sum of all list elements
(define (sum-of-list number-list)
  (if (empty? number-list)
      0
      (+ (first number-list) (sum-of-list (rest number-list)))))

(check-expect (sum-of-list (list 1 2 3 4 5)) 15)
(check-expect (sum-of-list empty) 0)
(check-expect (sum-of-list (list 2)) 2)

;; Type: (list of ANY) -> natural
;; Returns: Size of a list
(define (number-of-list-elements lst)
  (if (empty? lst)
      0
      (+ 1 (number-of-list-elements (rest lst)))))

(check-expect (number-of-list-elements empty) 0)
(check-expect (number-of-list-elements (list 1 2 "drei" 4 5)) 5)
(check-expect (number-of-list-elements (list true)) 1)

;; Type: (list of student) -> (list of natural)
;; Returns: List of student ages
(define (get-student-ages student-list student-ages)
  (if (empty? student-list)
      student-ages
      (get-student-ages (rest student-list) (append student-ages (list (person-age (student-person (first student-list))))))))

(check-expect (get-student-ages (list (make-student (make-person 21 "male") "12674") (make-student (make-person 18 "female") "347687236")) empty) (list 21 18))
(check-expect (get-student-ages empty empty) empty)
(check-expect (get-student-ages (list (make-student (make-person 17 "male") "8237687") (make-student (make-person 17 "female") "198365")) (list 20 28 25)) (list 20 28 25 17 17))

;; Type: (list of student) -> number
;; Precondition: List of student is not emtpy
;; Returns: Mean of all students ages
(define (mean-of-ages student-list)
  (/ (sum-of-list (get-student-ages student-list empty)) (number-of-list-elements student-list)))

(check-expect (mean-of-ages (list (make-student (make-person 21 "male") "12674") (make-student (make-person 18 "female") "347687236"))) (/ 39 2))
(check-expect (mean-of-ages (list (make-student (make-person 17 "male") "8237687") (make-student (make-person 17 "female") "198365"))) 17)
(check-error (mean-of-ages empty))