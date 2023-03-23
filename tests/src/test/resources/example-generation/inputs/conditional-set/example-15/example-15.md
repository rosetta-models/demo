Example 15:
Conditional mapping using "set" with a "when" clause that is predicated on the value of a xml element at the specified
xml path with multi-cardinality and meta.
The attribute EngineSpecification is conditional set from xml path engineType->engineDetail when  "engineType->
engineDetail->combustible->scheme" = "gasScheme"