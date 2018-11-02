;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V4) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: number -> (number -> number)
;; Returns: A funnctions multiplying a given number by x
(define (z x)
  ;; Type: number -> number
  ;; Returns: y multiplied by x
  (lambda (y) (* x y)))

((z 3) 4)