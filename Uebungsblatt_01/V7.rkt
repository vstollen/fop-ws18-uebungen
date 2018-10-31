;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V7) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct abc (a b))

(define (foo p)
  (if (abc? p)
      (if (list? (abc-b p))
          (list (abc-a p) (abc-b p))
          false)
      false))

(check-expect (foo (make-abc 4 (list 3 5 7))) (list 4 (list 3 5 7)))
(check-expect (foo (make-abc 1 2)) false)
(check-expect (foo "Ente") false)