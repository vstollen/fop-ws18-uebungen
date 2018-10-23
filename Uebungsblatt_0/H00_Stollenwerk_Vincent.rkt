;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H00_Stollenwerk_Vincent) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: natural string string -> string
;; Returns: Correct filename for homework submission
(define (generate-filename nr ln fn)
  (string-append "H" (if (> nr 9) "" "0") (number->string nr) "_" ln "_" fn ".rkt"))

(check-expect (generate-filename 0 "Stollenwerk" "Vincent") "H00_Stollenwerk_Vincent.rkt")
(check-expect (generate-filename 9 "Müller" "Hans") "H09_Müller_Hans.rkt")
(check-expect (generate-filename 13 "Mayer" "Gertrud") "H13_Mayer_Gertrud.rkt")