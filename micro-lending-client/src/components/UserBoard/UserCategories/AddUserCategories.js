import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import classnames from "classnames";
import {addUserCategories} from "../../../actions/backlogActions";
import PropTypes from "prop-types";

class AddUserCategories extends Component {

    constructor(props){
        super(props);
        const {id} = this.props.match.params; //gets the param from the url

        this.state = {
            categoryName: null,
            priority:0,
            Totalexpenditure:0,
            userIdentifier: id,
            errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit= this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.errors){
            this.setState({errors: nextProps.errors});
        }
    }


    onChange(e){
        //dynamically takes the input field name and value (when the input field changes)
        // and sets and matched the name and the value fields (in input html form ) to
        // that of the state.
        this.setState({[e.target.name]:e.target.value});
    }

    onSubmit(e){
        e.preventDefault();

        const sendUserCategoryDetails = {
            categoryName:this.state.categoryName,
            priority: this.state.priority,
            Totalexpenditure: this.state.Totalexpenditure,
            userIdentifier: this.state.userIdentifier
        };

        this.props.addUserCategories(
            this.state.userIdentifier,
            sendUserCategoryDetails,
            this.props.history
        );
        console.log(sendUserCategoryDetails);
    }

    render() {
        const {id}= this.props.match.params;
        const {errors} = this.state;

        return (
            <div className="add-PBI">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to={`/userboard/${id}`} className="btn btn-light">
                                Back to Project Board
                            </Link>
                            <h4 className="display-4 text-center">Add /Update Project Task</h4>
                            <p className="lead text-center">Project Name + Project Code</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className={classnames("form-control form-control-lg", {
                                        "is-invalid": errors.categoryName
                                    })} name="categoryName"
                                           placeholder="Category Name"
                                           value ={this.state.categoryName}
                                           onChange={this.onChange}
                                    />
                                    {errors.categoryName && (
                                        <div className="invalid-feedback">{errors.categoryName}</div>
                                    )}
                                </div>

                                <div className="form-group">
                                    <select className="form-control form-control-lg" name="priority"
                                    value = {this.state.priority}
                                            onChange={this.onChange}>
                                        <option value={0}>Select Priority</option>
                                        <option value={1}>High</option>
                                        <option value={2}>Medium</option>
                                        <option value={3}>Low</option>
                                    </select>
                                </div>

                                <div className="form-group">
                                    <input type="number" className="form-control form-control-lg" name="Totalexpenditure"
                                        placeholder="Total Expenditure"
                                    value={this.state.Totalexpenditure}
                                           onChange={this.onChange}
                                    />
                                </div>

                                <input type="submit"
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

AddUserCategories.propTypes = {
    addUserCategories: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state =>({
    errors: state.errors
});

export default connect(mapStateToProps, { addUserCategories })(AddUserCategories);

//export default connect(null, {addUserCategories})(AddUserCategories);