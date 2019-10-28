import React, { Component } from "react";
import ProjectItem from "./Project/projectItem";
import CreateProjectButton from "./Project/createProjectButton";
import { connect } from "react-redux";
import { getUsers } from "../actions/projectActions";
import PropTypes from "prop-types";
class Dashboard extends Component {
  componentDidMount() {
    this.props.getUsers();
  }

  render() {
    const userList= this.props.users;
    console.log(userList);
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              {userList.users.map(usr =>
                  (
                        <ProjectItem key={usr.id} user={usr} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  users: PropTypes.object.isRequired,
  getUsers: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  users: state.users
});
export default connect(
  mapStateToProps,
  { getUsers }
)(Dashboard);
