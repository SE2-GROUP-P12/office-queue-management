import { QMNavbar, QMSidebar } from './QMbars';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Customer, Employee, Manager } from './Actors'
import { Row } from 'react-bootstrap';
function App() {
  return (
    <Router>
      <QMNavbar />
      <Row>

        <QMSidebar />
        <Route exact path="/" render={() =>
          <Customer></Customer>
        } />
        <Route exact path="/employee" render={() =>
          <Employee></Employee>
        } />
        <Route exact path="/manager" render={() =>
          <Manager></Manager>
        } />

      </Row>

    </Router>
  );
}

export default App;
