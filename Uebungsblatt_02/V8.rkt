;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V8) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
(define-struct br (id pop type))

;; Type: (list of br) -> number
;; Precondition: Book types "Subscription" or "Single"
;; Returns: Total lending fee given list of br
(define (fee-total book-lending-list)
  ;; Type: br number -> number
  ;; Precondition: Book types "Subscription" or "Single"
  ;; Returns: Book fee added to a given number
  (foldr (lambda (book total-fee)
           (+ total-fee (if (string=? (br-type book) "Subscription")
                            1.5
                            (* (br-pop book) 1.75))))
         0
         book-lending-list))

(check-expect (fee-total (list (make-br 4 2 "Subscription") (make-br 7 3 "Single"))) 6.75)
(check-expect (fee-total empty) 0)
(check-expect (fee-total (list (make-br 38 5 "Subscription") (make-br 42 3 "Subscription") (make-br 4 1 "Single"))) 4.75)