import {Col} from 'react-bootstrap'
import {Button, Modal} from "react-bootstrap";
import {Dropdown, DropdownToggle, DropdownMenu, DropdownItem} from "react-bootstrap";
import {useEffect, useState} from "react";

function Customer() {
    const [modalShow, setModalShow] = useState(false);
    const [nextNumber, setNextNumber] = useState(-1);//prossimo numero da dare
    const [selectedService, setSelectedService] = useState(-1)
    const [serviceList, setServiceList] = useState([])
    useEffect(() => {
        fetch("/API/activeServices").then(response => {
            response.json().then(tmp => setServiceList(tmp))
        })
            .catch(error => {
                console.log(error);
            })

    }, [])
    const buttonHandler = () => {
        let tmp = {
            "serviceRequested": selectedService
        }
        fetch('/API/requestTicket', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tmp)
        }).then(resp => {
           resp.json().then(x => setNextNumber(x.clientNumber))
            setModalShow(true)
        })
    }
    return (
        <Col>
            <h1>Customer</h1>

            <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    {
                        selectedService === -1 ? "Select a service" : selectedService
                    }

                </Dropdown.Toggle>

                <Dropdown.Menu>
                    {serviceList.map((item) =>
                        <Dropdown.Item onSelect={() => {setSelectedService(item.servId); console.log(item.servId)} }>{item.servId}</Dropdown.Item>
                    )}
                </Dropdown.Menu>
            </Dropdown>
            <Button type="button" class="vertical-center" disabled={selectedService === -1} onClick={() => {
                buttonHandler()
            }}>Get your number</Button>
            <CustomerNumberModal show={modalShow} onHide={() => setModalShow(false)} number={nextNumber}/>
        </Col>
    );
}

function Employee() {
    const [nextNumber, setNextNumber] = useState(0);//prossimo numero da chiamare
    const [modalShow, setModalShow] = useState(false);
    const [counterNumber, setCounterNumber] = useState(2)// numero del bancone
    const buttonHandler = () => {
        fetch('/API/employee_getNext', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body:  '{ "deskId": '+ counterNumber + '}'})
        .then(resp => {
            resp.json().then(x => {
                if(x.messageType.action == "no more ticket to serve")
                    setNextNumber(0);
                else {
                    console.log(x.data);
                    setNextNumber(x.data.TicketToServe);
                }
                setModalShow(true)
        })
        }).catch(error => {console.log(error);})
    }
    return (
        <Col>
            <h1>Employee, counter {counterNumber}</h1>
            <Button type="button" class="vertical-center" onClick={() => buttonHandler()}>Call next customer</Button>
            <EmployeeNumberModal show={modalShow} onHide={() => setModalShow(false)} number={nextNumber}/>
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

function CustomerNumberModal(props) {
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
                    <div class="centered-text">Your number is</div>
                    <div class="number-container">{props.number}</div>
                </Col>

            </Modal.Body></Modal>
    )

}

function EmployeeNumberModal(props) {
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
                    {props.number==0 ? <div className="centered-text">No customers to serve</div> :
                        <div>
                        <div class="centered-text">The next customer number is</div>
                            <div class="number-container">{props.number}</div></div>
                    }
                </Col>

            </Modal.Body></Modal>
    )

}

export {Customer, Employee, Manager}