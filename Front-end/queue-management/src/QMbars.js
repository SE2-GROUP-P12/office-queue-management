
import { Navbar, Container, ListGroup, Col } from 'react-bootstrap';
import { NavLink } from 'react-router-dom'
function QMNavbar() {

    return (
        <Navbar bg="light" expand="lg">
            <Container>
                <Navbar.Brand href="#home">Queue Management</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
            </Container>
        </Navbar>
    );
}

function QMSidebar() {
    return (
        <Col md={2}>
            <ListGroup variant="flush" style={{paddingLeft:"10px"}}>
                <NavLink to="/" className='list-group-item' activeClassName='active' exact key="home">
                    Customer
             </NavLink>
                <NavLink to="/employee" className='list-group-item' activeClassName='active' exact key="employee">
                    Employee
             </NavLink>
                <NavLink to="/manager" className='list-group-item' activeClassName='active' exact key="manager">
                    Manager
             </NavLink>
            </ListGroup>
        </Col>
    );
}
export { QMNavbar, QMSidebar };