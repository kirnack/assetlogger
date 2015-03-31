<p>
<h2>Purpose</h2>
Program needed to schedule and checkout laptops to faculty and staff members and rent laptops to students.<br>
<br>
<h3>Definitions</h3>
<ul><li>Available: Not scheduled for another faculty or student during the time needed or in maintenance</li></ul>

<h1>Overall System</h1>
<h3>Functional Requirements</h3>
<ul><li>The system shall use barcodes to uniquely identify assets<br>
</li></ul><blockquote>Rationale: Both faculty laptops and student rentals are labeled with barcodes. The Help Desk has several scanners.<br>
</blockquote><ul><li>The system shall permit faculty and students to return assets before the scheduled due date<br>
</li><li>The system shall permit the return of an asset with the barcode<br>
</li></ul><blockquote>Rationale: This is the core piece of information needed to know an asset is returned.<br>
</blockquote><ul><li>The system shall only schedule assets if they are available for use<br>
</li><li>The system shall display information about a faculty or student through their I-number: Name, email, phone number<br>
</li><li>The system shall display information about an asset through its barcode: make, model, number, label<br>
</li><li>The system shall display past borrowers of an asset by the barcode<br>
</li><li>The system shall encrypt data sent across a network<br>
</li><li>The system shall protect the ability to check out an asset so that only Help Desk employees can perform the operation<br>
</li><li>The system shall distinguish searching for different asset types<br>
</li></ul><blockquote>Rational: Laptops for faculty and those for students are different. Also the Help Desk may be handling more than just laptops in the future.<br>
</blockquote><ul><li>The system shall permit only those who have I-numbers to obtain assets<br>
</li><li>The system shall permit both the help desk and call center to schedule assets<br>
</li></ul><blockquote>Rational: This takes offsets the work load from help desk employees onto call center employees<br>
</blockquote><ul><li>The system will display information about a faculty or student by their name: I-number, email, phone<br>
</li><li>The system will display assets that are currently being borrowed indicating the identity of the current borrower</li></ul>

<h3>Future Requirements</h3>
<ul><li>The system shall notify the faculty or student by email that a borrowed asset is near its due date the day before it is due<br>
</li><li>The system shall notify the faculty or student by email that a borrowed asset has past its due date the day after it is due<br>
</li><li>The system shall permit faculty and students to reserve an asset remotely<br>
</li><li>The system shall permit the admin to modify information about a faculty or student: phone, email<br>
</li><li>The system shall make assets that are in maintenance unavailable for use<br>
</li><li>The system shall permit help desk employees to change the maintenance status of an asset<br>
</li><li>The system may print a document for the faculty or student indicating when it is due</li></ul>


<h1>Faculty Checkout</h1>
<h3>Functional Requirements</h3>
<ul><li>The system shall schedule one or more assets for use by a faculty members<br>
</li><li>The system shall only schedule assets that are designated as checkout assets<br>
</li><li>The system shall check out an asset to a faculty member for a scheduled time<br>
</li><li>The system shall permit a scheduled asset to be checked out to a faculty member for the time it was scheduled for<br>
</li><li>The system shall permit a scheduled asset to change its scheduled time<br>
</li><li>The system shall schedule an asset to a faculty member for no more than two weeks at a time<br>
</li><li>The system shall permit a faculty member to extend a checked out asset for up to two more weeks<br>
</li><li>The system shall schedule assets with work days as the basic unit<br>
</li><li>The system may display past assets a faculty member has borrowed</li></ul>

<h3>Conditional Requirements</h3>
<ul><li>The system shall resolve conflicts when extending a checked out asset by attempting to allowing the faculty member requesting to extension to retain the asset and locate another asset for the other faculty member if such an asset exists; otherwise a new asset will be scheduled for the faculty member requesting to extend<br>
<h3>Future Requirements</h3>
</li><li>The system shall attempt to optimize scheduling such that the largest possible number of assets will be available at any given time<br>
</li><li>The system may keep track of students who pick up an asset for someone else<br>
</li><li>The system may display information about a teacher by their name: I-number, phone, email<br>
</li><li>The system may display assets borrowed by a teacher by their name<br>
<h1>Student Rental</h1>
<h3>Functional Requirements</h3>
</li><li>The system shall rent assets for use by students<br>
</li><li>The system shall only rent assets that are designated as a rental asset<br>
</li><li>The system shall permit students to rent assets for multiple semesters<br>
</li><li>The system shall require the student's I-number when the student checks out a rental<br>
</li><li>The system shall require the student's I-number when the student returns the rental<br>
</li><li>The system shall require the student to be present when checking out a rental<br>
</li><li>The system shall require the student to be present when returning a rental<br>
</li></ul><blockquote>Rational: Students need to go through an inspection process. They are harder to track down than faculty members if something is wrong with the asset.</blockquote>

<h3>Future Requirements</h3>
<ul><li>The system shall require an inspection of a rental when checked out<br>
</li><li>The system shall require an inspection of a rental when returned<br>
</li><li>The system may set the due date for student rentals to the last weekday before the last day of the last semester they are renting it for<br>
</li><li>The system may indicate the total cost for students to rent based on the scheduled time period