## My Personal Project: Nursing NEWS Score Calculator

##### What will the application do? 

The program should be able to create a chart  which contains patients and their corresponding  instances of “vital signs” readings. The vital signs will include respiratory rate, oxygen saturation, if the patient is using supplemental oxygen, temperature, blood pressure reading, heart rate, and AVPU score (AVPU is related to the patients level of consciousness). The application should be able to return a patients NEWS score (*National Early Warning Score*) based on the entered vital signs and alert if the patient is at risk of sepsis/or high risk. However, there should also be an option to declare if the NEWS score is the patients baseline status therefore de-activating alerts.
Here is a good description of how NEWS scoring works:

[NEWS SCORE INFORMATION](https://www.nurses.co.uk/blog/the-news-scale-explained/)

##### Who will use the application?
The targeted audience would be nurses. Although it would not be useful in the real-world it would be potentially useful for nursing students in simulation labs. 

##### Why is this project of interest to you?
I currently work as a nurse part-time; we chart this information in a platform called CST Cerner which also automatically calculates this score and provides alerts. However, there is no option to indicate that a NEWS score is a patients baseline/does not need to be reported to the doctor (as the doctor would already have been made aware after the first NEWS alert). Currently in CST Cerner for medical-surgical specific floors (non-ICU) documentation this makes it so that we are automatically directed to a place to document that we notified the doctor with no other options, forcing us to chart inaccurately. I would like to provide an option in my own project that allows for the identification of a baseline bypassing the need for alerts and only alerting again if the baseline score has increased.

##### User Stories:
-	As a user, I want to be able to add multiple vital signs readings to a patients record 
-	As a user, I want to be able to view a list of recorded vital signs for a patient 
-	As a user, I want to be able to have a NEWS score calculated for me 
-	As a user, I want to be alerted properly if my NEWS score requires intervention 
-	As a user, I want to be able to add multiple patients to the chart  
-	As a user, I want to be able to save my chart/patients and their corresponding vitals to a file (If I chose to do so)
-	As a user, I want to be able to load my chart/patients and their corresponding vitals to a file (If I chose to do so)

##### Instructions for End User:
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "View Patients Vitals List Oldest to Newest" button on the main menu 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "View Patients Vitals List Newest to Oldest" button on the main menu 
- You can generate the third action related to the user story "adding multiple Xs to a Y" by clicking the "View Patients Vitals List NEWS Score Above 5" button on the main menu 
- You can locate my visual component by entering vitals for a patient that have a NEWS score above 5, and then selecting that they are not within the patients baseline. 
- You can save the state of my application by clicking the "Save Chart" button on the main menu
- You can reload the state of my application by clicking the "Load Previous Chart" button on the main menu 

##### Image Citation:
- https://www.flaticon.com/free-icon/alert_10700437
- https://www.flaticon.com/free-icons/red-cross
