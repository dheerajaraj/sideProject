import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import userReducer from "./UserReducer";
//this will take in an object with all the reducers passed into comineReducers.
export default combineReducers({
  errors: errorReducer,
  users: userReducer
});
