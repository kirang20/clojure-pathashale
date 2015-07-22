;; Check if str is blank
(defn blank? [str]
  (every? #(Character/isWhitespace %) str))


;; Defining a Person type
(defrecord Person [first-name last-name])
;; Creating a Person
(def manasa (->Person 'Manasa' 'A'))


;; Hello user
(defn hello [name]
  (println (format "Hello, %s", name)))
(hello 'Abhi)
(hello "Anu")

;; In memory database of accounts
(def accounts (ref #{}))
(defrecord Account [id, balance])
(dosync
  (alter accounts conj (->Account 'CLJ 100.0)))

;; Calling Java methods
(.toUpperCase "hello")
(.."hello" getClass getProtectionDomain)


;; Creating Java Thread from clojure
(.start (new Thread (#(println "Hello" (Thread/currentThread)))))


;; Using Atom to keep track of state, using swap! to modify visitors
(def visitors (atom #{}))
(swap! visitors conj 'Maanu)


;; Hello which keeps track of all the visitors
(defn hello
  "Writes hello 'user' message to *out. Remembers you."
  [user]
  (swap! visitors conj user)
  (str "Hello, " user))

