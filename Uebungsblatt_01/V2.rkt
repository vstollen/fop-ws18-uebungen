;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V2) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define k (/ (sqrt 2) 12))

;; Type: number-> number
;; Return: Volume of a tetrahedron
(define (tetrahedron-volume a)
  (abs (* k (pow3 a))))

(check-within (tetrahedron-volume 5) 14.7 0.1)
(check-within (tetrahedron-volume -3) 3.1 0.1)
(check-within (tetrahedron-volume 10) 117.8 0.1)

;; Type: number -> number
;; Return: x to the power of 3
(define (pow3 x)
  (* x x x))

(check-expect (pow3 3) 27)
(check-expect (pow3 -2) -8)
(check-expect (pow3 0.5) 0.125)