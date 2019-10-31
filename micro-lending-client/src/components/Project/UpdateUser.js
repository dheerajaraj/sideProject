import React, { Component } from "react";
import { getUser, createUser } from "../../actions/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";

class UpdateUser extends Component {
  //make this a control component
  constructor() {
    super();

    this.state = {
      id: "",
      username: "",
      firstName: "",
      lastName: "",
      salary: "",
      bankAccount: "",
        errors: {}
    };
    this.onChange=this.onChange.bind(this);
    this.onSubmit=this.onSubmit.bind(this);
  }
  componentWillReceiveProps(nextProps) {
      if(nextProps.errors){
          this.setState({errors:nextProps.errors});
      }
    const {
      id,
      username,
      firstName,
      lastName,
      salary,
      bankAccount
    } = nextProps.user;
    this.setState({id,
        username,
        firstName,
        lastName,
        salary,
        bankAccount});
  }

  componentDidMount() {
    const id = this.props.match.params.id;
    this.props.getUser(id, this.props.history);
  }

  onChange(e){
      this.setState({[e.target.name]: e.target.value});
  }

  onSubmit(e){
    e.preventDefault();
    const updateUser={
        id: this.state.id,
        username: this.state.username,
        firstName: this.state.firstName,
        lastName: this.state.lastName,
        salary: this.state.salary,
        bankAccount: this.state.bankAccount
    };
      this.props.createUser(updateUser, this.props.history);
  }


  render() {
      const {errors} = this.state;
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Update Project form</h5>
              <hr />
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.username //bootstrap class to redden the text and border with error
                    })}
                    placeholder="User Name"
                    name="username"
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
                    className="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    disabled
                    value={this.state.id}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                    className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.firstName //bootstrap class to redden the text and border with error
                    })}
                    placeholder="First Name"
                    name="firstName"
                    value={this.state.firstName}
                    onChange={this.onChange}
                  ></input>
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
                    className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.lastName //bootstrap class to redden the text and border with error
                    })}
                    placeholder="Last Name"
                    name="lastName"
                    value={this.state.lastName}
                    onChange={this.onChange}
                  ></input>
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
                    className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.salary //bootstrap class to redden the text and border with error
                    })}
                    placeholder="Salary"
                    name="salary"
                    value={this.state.salary}
                    onChange={this.onChange}
                  ></input>
                    {
                        errors.salary && (
                            <div className="invalid-feedback">
                                {errors.salary}
                            </div>
                        )
                    }
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

const mapStateToProps = state => ({
  user: state.users.user,
    errors: state.errors
});

UpdateUser.propTypes = {
  getUser: PropTypes.func.isRequired,
    createUser: PropTypes.func.isRequired,
    user: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

export default connect(
  mapStateToProps,
  { getUser, createUser }
)(UpdateUser);
