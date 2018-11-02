;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V5) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct abc (a b))

;; Type: (list of ANY) -> (list of (boolean or (list of ANY)))
;; Returns: List with results of foo for every list element
(define (bar1 lst)
  (if (empty? lst)
      empty
      
      ;; Type: ANY -> boolean or (list of ANY)
      ;; Returns: List of abc-a and abc-b, when given abc with a list abc-b.
      ;;          Otherwise returns false.
      (cons ((lambda (p)
               (if (abc? p)
                   (if (list? (abc-b p))
                       (list (abc-a p) (abc-b p))
                       false)
                   false)
               )(first lst))
      (bar1 (rest lst)))))

(check-expect (bar1 empty) empty)
(check-expect (bar1 (list 6 4 (make-abc 6 (list 4)))) (list false false (list 6 (list 4))))
(check-expect (bar1 (list (make-abc (list 3 5) (list "Dies" "Das")) (make-abc 3 empty))) (list (list (list 3 5) (list "Dies" "Das")) (list 3 empty)))