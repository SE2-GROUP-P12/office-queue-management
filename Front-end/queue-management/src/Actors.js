import {Col} from 'react-bootstrap'
import {Button, Modal} from "react-bootstrap";
import { useState} from "react";
function Customer() {
    const [modalShow, setModalShow] = useState(false);
    return (
        <Col>
            <h1>Customer</h1>
            <Button type="button" class="vertical-center" onClick={() => setModalShow(true)}>Get your number</Button>
            <CustomerNumberModal show={modalShow} onHide={() => setModalShow(false)}/>
        </Col>
    );
}
function Employee() {
    const [modalShow, setModalShow] = useState(false);
    return (
        <Col>
            <h1>Employee</h1>
            <Button type="button" class="vertical-center" onClick={() => setModalShow(true)}>Call next customer</Button>
            <EmployeeNumberModal show={modalShow} onHide={() => setModalShow(false)}/>
        </Col>
    );
}
function Manager() {
    return (
        <Col>
            <h1>Manager</h1>
        </Col>
    );
}

function CustomerNumberModal(props){
    // l'api dovrebbe chiedere il numero qui
    return (
        <Modal
            {...props}
            size="lg"
            
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            
            <Modal.Body>
                <Col>
                <div class= "centered-text" >Your number is </div>
                <div class = "number-container" >1</div>
                </Col>

            </Modal.Body></Modal>
    )
    
}
function EmployeeNumberModal(props){
    // l'api dovrebbe chiedere il numero qui
    return (
        <Modal
            {...props}
            size="lg"
            
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            
            <Modal.Body>
                <Col>
                <div class= "centered-text" >The next customer number is </div>
                <div class = "number-container" >1</div>
                </Col>

            </Modal.Body></Modal>
    )
    
}
export  {Customer, Employee, Manager} 