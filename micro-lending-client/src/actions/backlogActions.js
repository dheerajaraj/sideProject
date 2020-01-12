import axios from "axios";
import {GET_ERRORS} from "./types";
export const addUserCategories = (id, userCategory, history)=>
    async dispatch => {
    try{
        await axios.post(`/api/categories/${id}`, userCategory);
        history.push(`/UserBoard/${id}`);
        dispatch({
            type: GET_ERRORS,
            payload:{}
        });
    } catch (err){
            dispatch({
                type: GET_ERRORS,
                payload: err.response.data
            });
        }
    };
