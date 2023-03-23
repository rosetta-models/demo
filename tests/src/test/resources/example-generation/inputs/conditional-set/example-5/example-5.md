Example 5:

Two conditional mapping synonyms on a single attribute, each using "set" with a "when" clause that is predicated on the
value of a xml element at the specified xml path.

The attribute EngineSpecification->alternativeFuelType is conditionally set from:

- xml path engine->engineType->engineDetail->complementaryEnergy when the value at xml path engine->engineType->
  engineDetail->energyCategory is "HYBRID"
- xml path engine->engineType->engineDetail->combustible when the value at xml path engine->engineType->engineDetail->
  energyCategory is "HYDROCARBON"