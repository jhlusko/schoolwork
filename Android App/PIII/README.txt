Instructions to run the Triage app by group T5101-09 (group_0727):

List of valid Usernames/Passowords can be found at group_0727\PII\passwords.txt
use username: "TA", password: "phase2". Alternatively, a new user may be registered through the 
app interphase.

All the back-end files are located at group_0727\PII\Triage\src\triage
All the android related files are located at group_0727\PII\Triage\src\com\example\androidUI

First activity is the login screen. After re-loading
the app without re-installing all registered users will be loaded up for sign-in.
After logging in, all data is automatically loaded to the EmergencyRoom object.

On the second Activity, the user is given the option of recording a new patient, saving all data
or looking up a Patient by Health Card number.

Follow prompts for recording a patient. Then press the record button at the bottom

For recording Vitals, the patinet has to be retrieved by Heath Card number, then the user has the
option of displaying the current patient data (this feature will be expanded upon later) or
recording vitals.

Follow prompts for recording vitals within this activity screen. Then press record button at the bottom
