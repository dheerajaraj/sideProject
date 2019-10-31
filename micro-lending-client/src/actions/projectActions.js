import axios from "axios"; // library imported to talk to backend
import {GET_ERRORS, GET_USER, GET_USERS, DELETE_USER} from "./types";

export const createUser = (user, history) => async dispatch => {
  try {
    const res = await axios.post("/api/user", user);
    history.push("/dashboard"); //redirecting to the dashboard once the user is added/created
    //the error reducer function has a state and if we do not clear the payload of that state,
    //then when the updateproject.js file is called, then we will GET_ERRORS and that the
    // errors from the previous state will still be persisted.
    dispatch({
      type:GET_ERRORS,
      payload:{}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getUsers = () => async dispatch =>{
  const res = await axios.get("/api/user/all");
  dispatch({
    type: GET_USERS,
    payload: res.data
  })
};

export const getUser = (id, history) => async dispatch =>{
  const res = await axios.get(`/api/user/${id}`);
  dispatch({
    type: GET_USER,
    payload: res.data
  })
};

export const deleteUser = id => async dispatch => {
  if(
      window.confirm("Are you sure you want to delete the user with all the data associated with it?")
  )
  await axios.delete(`/api/user/${id}`);
  dispatch({
    type: DELETE_USER,
    payload: id
  });
}
