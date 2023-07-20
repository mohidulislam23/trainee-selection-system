import React, { useState } from 'react';
import axios from 'axios';
import jsPDF from 'jspdf'; // Import the jsPDF library
import 'jspdf-autotable'; // Import the jspdf-autotable library
import './ApplicantRegister.scss'; // Import the SCSS file

const ApplicantRegister = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [gender, setGender] = useState('');
    const [dob, setDob] = useState('');
    const [email, setEmail] = useState('');
    const [contactNumber, setContactNumber] = useState('');
    const [degreeName, setDegreeName] = useState('Bachelor\'s Degree');
    const [educationalInstitute, setEducationalInstitute] = useState('');
    const [cgpa, setCgpa] = useState('');
    const [passingYear, setPassingYear] = useState('');
    const [presentAddress, setPresentAddress] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [applicantId, setApplicantId] = useState(null);



    const handleSubmit = (e) => {
        e.preventDefault();
        // Create the applicant object with the entered data
        const applicantData = {
            firstName,
            lastName,
            gender,
            dob,
            email,
            contactNumber,
            degreeName,
            educationalInstitute,
            cgpa,
            passingYear,
            presentAddress,
        };

        // Send a POST request to register the applicant
        axios.post('http://localhost:8080/applicant/', applicantData)
            .then((response) => {
                // Get the applicant ID from the response and display it
                setApplicantId(response.data.applicantId);
                setSuccessMessage('Applicant registered successfully.');
                setErrorMessage('');
                // Clear the form after successful submission
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setGender(response.data.gender);
                setDob(response.data.dob);
                setEmail(response.data.email);
                setContactNumber(response.data.contactNumber);
                setDegreeName(response.data.degreeName);
                setEducationalInstitute(response.data.educationalInstitute);
                setCgpa(response.data.cgpa);
                setPassingYear(response.data.passingYear);
                setPresentAddress(response.data.presentAddress);
            })
            .catch((error) => {
                setErrorMessage('Error registering applicant. Please try again.');
                setSuccessMessage('');
            });
    };

    // const handleGeneratePDF = () => {
    //     if (applicantId) {
    //         // Create a new instance of jsPDF
    //         const pdf = new jsPDF();

    //         // Add the watermark image to the PDF
    //         const watermarkImageUrl = './src/assets/bjit.png'; // Replace with the actual path to your watermark image
    //         const watermarkWidth = 40; // Adjust the width of the watermark image as needed
    //         const watermarkHeight = (watermarkWidth * 7) / 6; // Maintain the aspect ratio of the watermark image
    //         const xPosition = (pdf.internal.pageSize.getWidth() - watermarkWidth + 1) / 1; // Center the watermark horizontally
    //         const yPosition = (pdf.internal.pageSize.getHeight() - watermarkHeight) / 30; // Center the watermark vertically
    //         pdf.addImage(watermarkImageUrl, 'PNG', xPosition, yPosition, watermarkWidth, watermarkHeight);

    //         // Define the content to be included in the PDF
    //         const pdfData = `Applicant ID: ${applicantId}\nFull Name: ${firstName} ${lastName}\nEmail: ${email}\nContact Number: ${contactNumber}\nDegree Name: ${degreeName}\nCGPA: ${cgpa}\nPassing Year: ${passingYear}\nPresent Address: ${presentAddress}`;
    //         // const pdfData = `Applicant ID: ${applicantId}\nFull Name: ${firstName} ${lastName}\nEmail: ${email}\nContact Number: ${contactNumber}\nDegree Name: ${degreeName}\nCGPA: ${cgpa}\nPassing Year: ${passingYear}\nPresent Address: ${presentAddress}`;

    //         // Add the content to the PDF
    //         pdf.text(10, 10, pdfData);

    //         // Save the PDF with a filename
    //         pdf.save('applicant_details.pdf');
    //     }
    // };

    const handleGeneratePDF = () => {
        if (applicantId) {
            const pdf = new jsPDF();

            // Add the watermark image to the PDF
            const watermarkImageUrl = './src/assets/bjit.png';
            const watermarkWidth = 40;
            const watermarkHeight = (watermarkWidth * 21) / 20;
            const xPosition = (pdf.internal.pageSize.getWidth() - watermarkWidth) / 2;
            const yPosition = (pdf.internal.pageSize.getHeight() - watermarkHeight) / 30;
            pdf.addImage(watermarkImageUrl, 'PNG', xPosition, yPosition, watermarkWidth, watermarkHeight);

            // Define the content to be included in the PDF as a table
            const tableData = [
                ['Applicant ID', applicantId],
                ['Full Name', `${firstName} ${lastName}`],
                ['Email', email],
                ['Contact Number', contactNumber],
                ['Degree Name', degreeName],
                ['CGPA', cgpa],
                ['Passing Year', passingYear],
                ['Present Address', presentAddress],
            ];

            // Add the table to the PDF
            pdf.autoTable({
                head: [['Attribute', 'Value']],
                body: tableData,
                startY: 60, // Adjust the vertical position of the table in the PDF
            });

            // // Add the title at the end of the PDF
            // const title = 'Applicant Register Details';
            // const titleX = pdf.internal.pageSize.getWidth() / 2;
            // const titleY = pdf.internal.pageSize.getHeight() - 20;
            // pdf.text(titleX, titleY, title, { align: 'center' });

            // Save the PDF with a filename
            pdf.save('applicant_details.pdf');
        }
    };


    return (
        <div className="applicant-register">
            <h2>Applicant Registration</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="firstName">First Name:</label>
                    <input
                        type="text"
                        id="firstName"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="lastName">Last Name:</label>
                    <input
                        type="text"
                        id="lastName"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="gender">Gender:</label>
                    <select
                        id="gender"
                        value={gender}
                        onChange={(e) => setGender(e.target.value)}
                        required
                    >
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div>
                    <label htmlFor="dob">Date of Birth:</label>
                    <input
                        type="date"
                        id="dob"
                        value={dob}
                        onChange={(e) => setDob(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="contactNumber">Contact Number:</label>
                    <input
                        type="tel"
                        id="contactNumber"
                        value={contactNumber}
                        onChange={(e) => setContactNumber(e.target.value)}
                        pattern="[0-9]{9,}"
                        required
                    />
                </div>
                <div>
                    <label htmlFor="degreeName">Degree Name:</label>
                    <select
                        id="degreeName"
                        value={degreeName}
                        onChange={(e) => setDegreeName(e.target.value)}
                        required
                    >
                        <option value="Bachelor's Degree">Bachelor's Degree</option>
                        <option value="Master's Degree">Master's Degree</option>
                        <option value="Ph.D.">Ph.D.</option>
                        {/* Add more degree options if needed */}
                    </select>
                </div>
                <div>
                    <label htmlFor="educationalInstitute">Educational Institute:</label>
                    <input
                        type="text"
                        id="educationalInstitute"
                        value={educationalInstitute}
                        onChange={(e) => setEducationalInstitute(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="cgpa">CGPA:</label>
                    <input
                        type="number"
                        step="0.01"
                        id="cgpa"
                        value={cgpa}
                        onChange={(e) => setCgpa(e.target.value)}
                        min="0"
                        max="4"
                        required
                    />
                </div>
                <div>
                    <label htmlFor="passingYear">Passing Year:</label>
                    <input
                        type="number"
                        id="passingYear"
                        value={passingYear}
                        onChange={(e) => setPassingYear(e.target.value)}
                        min="1980"
                        max="2023"
                    // Passing year is optional, so it's not required
                    />
                </div>
                <div>
                    <label htmlFor="presentAddress">Present Address:</label>
                    <textarea
                        id="presentAddress"
                        value={presentAddress}
                        onChange={(e) => setPresentAddress(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <button type="submit">Register</button>
                </div>
            </form>


            {successMessage && <p className="success-message">{successMessage}</p>}
            {errorMessage && <p className="error-message">{errorMessage}</p>}

            {applicantId && (
                <div>
                    <p>Applicant ID: {applicantId}</p>
                    <button onClick={handleGeneratePDF}>Save as PDF</button>
                </div>
            )}
        </div>
    );
};

export default ApplicantRegister;
