;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V7) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define (cartesian-prod lst-x lst-y)
  (map (lambda (x)
         )
       lst-x)

(check-expect (cartesian-prod (list 1 2) (list 3 4)) (list (list 1 3) (list 1 4) (list 2 3) (list 2 4)))