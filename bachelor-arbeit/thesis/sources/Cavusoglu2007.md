Cavusoglu derives a *mathematical* model for choosing the right disclosure policy.

# Disclosure Policies

No disclosure policy is *always* optimal, the characteristics of the vulnerability (risk, user population) and the vendor's incentive determine the optimal vulnerability disclosure.

## Full Vendor Disclosure
Full Vendor Disclosure means immediately informing *only* the vendor about the vulnerability.
This may put the public at risk, as the vendor might choose not to patch the vulnerability, or take a long time to fix the vulnerability.

## Immediate Public Disclosure
Immediate Public Disclosure means making the vulnerability public immediately after found.
The vendor is then forced to fix the vulnerability as soon as possible to avoid public embarrasment.
Public disclosure allows affected users to take countermeasures immediately, but also gives hackers the opportunity to develop exploits and attack vulnerable systems.

## Hybrid Disclosure
Hybrid Disclosure is a hybrid of the two disclosure policies above.
It means informing the vendor about the vulnerability but also setting a fixed grace period, in wich the vendor is expected to develop a patch for the vulnerability.
If the grace period passes without the vendor releasing a patch, the vulnerability is released to the public.
If the vendor releases a patch before the end of the grace period, the vulnerability is released to the public at patchday.

# CERT/CC

The Computer Emergency Response Team/Coordination Center CERT/CC is a third party coordinator to manage hybrid vulnerability disclosure process.
A user finding an vulnerability informs CERT/CC, and after verifiying the vulnerability, CERT/CC informs the vendor and sets the grace period.

