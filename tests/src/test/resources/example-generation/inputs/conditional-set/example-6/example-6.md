Example 6:

Conditional mapping using "set" with a "when" clause that is predicated on the upstream xml path.

- The attribute Terminology->combustibleTaxBand is conditionally set from xml element combustible when the upstream xml
  path engine->usEngineSpecification->engineDetail.
- The attribute Terminology->volume is conditionally set from xml element capacity when the upstream xml path engine->
  ukEngineSpecification->engineDetail.