;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: number -> number
;; Return: Temperature in degree Celsius given a temperature in degree Fahrenheit
(define (fahr->cel temperature-in-fahrenheit)
  (* (- temperature-in-fahrenheit 32) (/ 5 9)))

(check-within (fahr->cel 100) 37.7 0.1)
(check-expect (fahr->cel 32) 0)
(check-expect (fahr->cel 212) 100)