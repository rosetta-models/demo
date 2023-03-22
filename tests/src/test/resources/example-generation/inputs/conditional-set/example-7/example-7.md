Example 7:

Conditional mapping using "set" with a "when" clause that is predicated on the upstream rosetta path.

- The attribute Terminology->combustibleTaxBand is conditionally set from xml element combustible when the upstream
  rosetta path is Root->usEngineVersion->terminology.
- The attribute Terminology->volume is conditionally set from xml element capacity when the upstream rosetta path is
  Root->ukEngineVersion->terminology.