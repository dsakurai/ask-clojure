(ns askpath.ask
  (:require [cheshire.core   :as json        ]
            [clojure.java.io :as io          ]
            [cli-matic.core  :refer [run-cmd]]
            )
  (:import (com.networknt.schema JsonSchemaFactory SpecVersion$VersionFlag JsonSchema ValidatorTypeCode)
           (com.fasterxml.jackson.databind ObjectMapper))
  )

(defn validate-schema [schema-stream json-stream]
  (let [mapper (ObjectMapper.)
        factory (JsonSchemaFactory/getInstance SpecVersion$VersionFlag/V7)
        schema (.getSchema factory schema-stream)
        node (.readTree mapper json-stream)
        errors (.validate schema node)
        ]
    {:errors errors :node node}
  ))

(defn print-all [& _]
    (let [snippets-file-demo "snippets.json"
          snippets-file-env (System/getenv "ASK_SNIPPET_FILE")
          snippets-file-path (if (nil? snippets-file-env) ;; It's better to try to load the file here. 
                                                          ;; Currently, we're assuming that env var set == file exists.
                                 (do (println "The snippets file is not found! Using a demo file instead.") snippets-file-demo)
                                 snippets-file-env)
          snippets-json-str (slurp snippets-file-path)
          validation-result (validate-schema
                    (slurp "snippets-schema.json") ;; Might be slower than (io/input-stream "filename")
                    snippets-json-str
                   )
          errors (:errors validation-result)
          snippets-map (json/parse-string snippets-json-str)
          ]
      (if (.isEmpty errors)
        (do
          (println (get snippets-map "entries"))
          (println (System/getenv "ASK_SNIPPET_FILE"))
          )
        ;; Print errors
        (do
          (println "JSON file does not follow the schema:")
          (doseq [err errors]
            (println (.getMessage err))))))
  )

(defn -main [& args]
  (run-cmd args {:app {:command "ask-clojure"
                       :description "Snippet manager written with Clojure"}
                 :commands [{:command "debug"
                             :description "Print debug information"
                             :runs print-all
                             }
                            ]}
           ))

