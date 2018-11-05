;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V8) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
(define-struct br (id pop type))

;; Type: (list of br) -> number
;; Returns: Total lending fee given list of br
(define (fee-total book-lending-list)
  ;; Type: br number -> number
  ;; Returns: Book fee added to a given number
  (foldr (lambda (br total-fee)
           (+ total-fee (if (= br-type "Subscription")
                            1.5
                            (* br-pop 1.75))))
         0
         book-lending-list))

(check-expect (fee-total (list (make-br 4 2 "Subscription") (make-br 7 3 "Single"))) 6.75)