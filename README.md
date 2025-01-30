## My Personal Project: Nursing NEWS Score Calculator

##### What will the application do? 

The program should be able to create a chart  which contains patients and their corresponding  instances of “vital signs” readings. The vital signs will include respiratory rate, oxygen saturation, if the patient is using supplemental oxygen, temperature, blood pressure reading, heart rate, and AVPU score (AVPU is related to the patients level of consciousness). The application should be able to return a patients NEWS score (*National Early Warning Score*) based on the entered vital signs and alert if the patient is at risk of sepsis/or high risk. However, there should also be an option to declare if the NEWS score is the patients baseline status therefore de-activating alerts.
Here is a good description of how NEWS scoring works:

[NEWS SCORE INFORMATION](https://www.nurses.co.uk/blog/the-news-scale-explained/)

##### Who will use the application?
The targeted audience would be nurses. Although it would not be useful in the real-world it would be potentially useful for nursing students in simulation labs. 

##### Why is this project of interest to you?
I currently work as a nurse part-time; we chart this information in a platform called CST Cerner which also automatically calculates this score and provides alerts. However, there is no option to indicate that a NEWS score is a patients baseline/does not need to be reported to the doctor (as the doctor would already have been made aware after the first NEWS alert). Currently in CST Cerner for medical-surgical specific floors (non-ICU) documentation this makes it so that we are automatically directed to a place to document that we notified the doctor with no other options, forcing us to chart inaccurately. I would like to provide an option in my own project that allows for the identification of a baseline bypassing the need for alerts and only alerting again if the baseline score has increased.