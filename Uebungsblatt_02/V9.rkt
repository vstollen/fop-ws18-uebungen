;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V9) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ())))
(define total-min 180)
(define homework-min 50)
(define test-min 35)
(define project-min 50)

;; Type: (list of number) (list of (list of number number number)) -> (list of number)
;; Precondition: matrikel-list and score-list have the same amount of entrys and are ordered
;;               that the matrikel-nr matches the scores at a given point in the lists
;; Returns: List of the matrikel numbers of the students that passed
(define (passed matrikel-list score-list)
  ;; Type: number or boolean -> boolean
  ;; Returns: true if not a boolean
  (filter (lambda (result)
            (if (boolean? result)
                false
                true))
   ;; Type: number number -> number or boolean
   ;; Returns: Matrikel number, if the scores match the passing conditions
   ;;          otherwise returns false
   (map (lambda (matrikel-nr scores)
         (if (< (foldr + 0 scores) total-min)
             false
             (if (< (first scores) homework-min)
                 false
                 (if (< (first (rest scores)) test-min)
                     false
                     (if (< (first (rest (rest scores))) project-min)
                         false
                         matrikel-nr)))))
       matrikel-list
       score-list)))

(check-expect (passed (list 1 5 8 20) (list (list 80 60 70) (list 60 40 60) (list 50 35 100) (list 100 70 50))) (list 1 8 20))
(check-expect (passed empty empty) empty)
(check-expect (passed (list 5 42 128) (list (list 49 35 100) (list 50 34 100) (list 100 35 49))) empty)