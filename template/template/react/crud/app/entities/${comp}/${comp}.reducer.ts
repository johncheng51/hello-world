import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { I${ucomp}, defaultValue } from 'app/shared/model/${comp}.model';

export const ACTION_TYPES = {
  FETCH_ADDRESS_LIST: '${comp}/FETCH_ADDRESS_LIST',
  FETCH_ADDRESS: '${comp}/FETCH_ADDRESS',
  CREATE_ADDRESS: '${comp}/CREATE_ADDRESS',
  UPDATE_ADDRESS: '${comp}/UPDATE_ADDRESS',
  DELETE_ADDRESS: '${comp}/DELETE_ADDRESS',
  RESET: '${comp}/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<I${ucomp}>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ${ucomp}State = Readonly<typeof initialState>;

// Reducer

export default (state: ${ucomp}State = initialState, action): ${ucomp}State => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADDRESS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADDRESS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADDRESS):
    case REQUEST(ACTION_TYPES.UPDATE_ADDRESS):
    case REQUEST(ACTION_TYPES.DELETE_ADDRESS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADDRESS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADDRESS):
    case FAILURE(ACTION_TYPES.CREATE_ADDRESS):
    case FAILURE(ACTION_TYPES.UPDATE_ADDRESS):
    case FAILURE(ACTION_TYPES.DELETE_ADDRESS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADDRESS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADDRESS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADDRESS):
    case SUCCESS(ACTION_TYPES.UPDATE_ADDRESS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADDRESS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/addresses';

// Actions

export const getEntities: ICrudGetAllAction<I${ucomp}> = (page, size, sort) => {
  const requestUrl = `@@{apiUrl}@@{sort ? `?page=@@{page}&size=@@{size}&sort=@@{sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ADDRESS_LIST,
    payload: axios.get<I${ucomp}>(`@@{requestUrl}@@{sort ? '&' : '?'}cacheBuster=@@{new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<I${ucomp}> = id => {
  const requestUrl = `@@{apiUrl}/@@{id}`;
  return {
    type: ACTION_TYPES.FETCH_ADDRESS,
    payload: axios.get<I${ucomp}>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<I${ucomp}> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADDRESS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<I${ucomp}> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADDRESS,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<I${ucomp}> = id => async dispatch => {
  const requestUrl = `@@{apiUrl}/@@{id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADDRESS,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
