import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createUser } from "../../actions/projectActions";
import classnames from "classnames";

class AddProject extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      firstName: "",
      lastName: "",
      salary: "",
      bankAccount: "",
      errors: {}
    };
    this.onChange = this.onUserName.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  //lifecycle hooks is used to get data from state whenever state changes
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onUserName(e) {
    //dynamically takes the input field name and value (when the input field changes)
    // and sets and matched the name and the value fields (in input html form ) to
    // that of the state.
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const sendUserDetails = {
      username: this.state.username,
      firstName: this.state.firstName,
      lastName: this.state.lastName,
      salary: this.state.salary,
      bankAccount: this.state.bankAccount
    };

    this.props.createUser(sendUserDetails, this.props.history); // props.history will redirect the user to /dashboard in createUser method
  }

  render() {
    const { errors } = this.state;

    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">
                Create / Edit Project form
              </h5>
              <hr />
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.username //bootstrap class to redden the text and border with error
                    })}
                    placeholder="User Name"
                    name="username" //Must match with the attribute given on the backend.
                    value={this.state.username}
                    onChange={this.onChange}
                  />
                  {
                  errors.username && (
                      <div className="invalid-feedback">
                        {errors.username}
                      </div>
                  )
                }
                </div>

                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.firstName //bootstrap class to redden the text and border with error
                    })}
                    placeholder="First Name"
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.onChange}
                  />
                  {
                    errors.firstName && (
                        <div className="invalid-feedback">
                          {errors.firstName}
                        </div>
                    )
                  }
                </div>

                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.lastName //bootstrap class to redden the text and border with error
                    })}
                    placeholder="Last Name"
                    name="lastName"
                    value={this.state.lastName}
                    onChange={this.onChange}
                  />
                  {
                    errors.lastName && (
                        <div className="invalid-feedback">
                          {errors.lastName}
                        </div>
                    )
                  }
                </div>

                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Monthly Salary"
                    name="salary"
                    value={this.state.salary}
                    onChange={this.onChange}
                  />
                  <p>{errors.salary}</p>
                </div>

                <div className="form-group">
                  <input
                    className="form-control form-control-lg"
                    placeholder="Bank Account Number"
                    name="bankAccount"
                    value={this.state.bankAccount}
                    onChange={this.onChange}
                  />
                  <p>{errors.bankAccount}</p>
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
//Telling that createUser prop is required for this component to work properly
AddProject.propTypes = {
  createUser: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { createUser }
)(AddProject);
