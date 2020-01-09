import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateUser from "./components/Project/UpdateUser";
import UserBoard from "./components/UserBoard/userboard";
import AddUserCategories from "./components/UserBoard/UserCategories/AddUserCategories";
class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/Dashboard" component={Dashboard} />
            <Route exact path="/AddProject" component={AddProject} />
            <Route exact path="/UpdateUser/:id" component={UpdateUser}/>
            <Route exact path="/UserBoard/:id" component={UserBoard}/>
            <Route exact path="/addUserCategories/:id" component={AddUserCategories}/>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
