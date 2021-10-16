import {Col} from 'react-bootstrap'
import {Button, Modal} from "react-bootstrap";
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from "react-bootstrap";
import { useEffect, useState} from "react";
function Customer() {
    const [modalShow, setModalShow] = useState(false);
    const [nextNumber, setNextNumber] = useState(0);//prossimo numero da dare
    const [serviceList, setServiceList]  = useState([{"name": "Payment", "id":1 }, {"name":"Consulting", "id":2 }, {"name": "placeholder", "id":3 }])
    /*useEffect(()=>{
        fetch("/API/custome_loadServices/").then(response=>{
            setServiceList(response)
        }) .catch(error => {
            console.log(error);
            updateSurveyList(()=>[]);
            
        })
    
    })*/
    const buttonHandler= ()=>{
        setNextNumber(nextNumber+1); 
        setModalShow(true)
        let tmp = {
            "number": nextNumber,
            "service": "placeholder"
        }
        /*
        fetch('/API/customer_newNumber', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tmp)
        })*/ 
    }
    return (
        <Col>
            <h1>Customer</h1>
            
<Dropdown>
  <Dropdown.Toggle variant="success" id="dropdown-basic">
    Select a service
  </Dropdown.Toggle>

  <Dropdown.Menu>
  {serviceList.map((item)=>
        <Dropdown.Item href={"#/action-"+ item.id}>{item.name}</Dropdown.Item>
        )}
  </Dropdown.Menu>
</Dropdown>

            
            
            <Button type="button" class="vertical-center" onClick={() => {buttonHandler() }}>Get your number</Button>
            <CustomerNumberModal show={modalShow} onHide={() => setModalShow(false)}number ={nextNumber}/>
        </Col>
    );
}
function Employee() {
    const [nextNumber, setNextNumber] = useState(0);//prossimo numero da chiamare
    const [modalShow, setModalShow] = useState(false);
    const [counterNumber, setCounterNumber] = useState(0)// numero del bancone
    const buttonHandler = () => {
        setModalShow(true);
    /*scheletro per la chiamata a api 
    useEffect(()=>{
        fetch("/API/employee_getNext/"+counterNumber).then(response=>{
            setNextNumber(response)
        }) .catch(error => {
            console.log(error);
            
            
        })
    
    })*/
    }
    return (
        <Col>
            <h1>Employee</h1>
            <Button type="button" class="vertical-center" onClick={() => buttonHandler()}>Call next customer</Button>
            <EmployeeNumberModal show={modalShow} onHide={() => setModalShow(false)} number = {nextNumber}/>
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
                <div class = "number-container" >{props.number}</div>
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
                <div class = "number-container" >{props.number}</div>
                </Col>

            </Modal.Body></Modal>
    )
    
}
export  {Customer, Employee, Manager} 