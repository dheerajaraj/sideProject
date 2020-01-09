import {
    DELETE_USER_CATEGORIES,
    GET_USER_CATEGORIES,
    GET_USER_CATEGORY
} from "../actions/types";

const initialState = {
    user_categories:[],
    user_category:{}
};

export default function(state=initialState, action){
    switch(action.type){
        case GET_USER_CATEGORIES:
            return {
                ...state,
                user_categories: action.payload
            }

        case GET_USER_CATEGORY:
            return {
                ...state,
                user_category: action.payload
            }

        case DELETE_USER_CATEGORIES:
            return {
                ...state
                //TODO
            };

        default:
            return state;
    }
}