import { combineReducers } from 'redux'
import exampleReducer from './slices/exampleSlice'
import authReducer from './slices/authSlice'
import companyReducer from './slices/companySlice'
import signUpReducer from './slices/signUpSlice'
import addressReducer from './slices/addressSlice'
import managerStateSlice from './slices/managerStateSlice'
import departmentreducer from './slices/departmentSlice'
import registCompanyReducer from './slices/registCompanySlice'
import initialPasswordReducer from './slices/initialPasswordSlice'
import managerReducer from './slices/managerSlice'
import transactionTypeReducer from './slices/transactionTypeSlice'
import inflowTypeReducer from './slices/inflowTypeSlice'
import scheduleTypeReducer from './slices/scheduleTypeSlice'

const rootReducer = combineReducers({
  scheduleType: scheduleTypeReducer,
  inflowType: inflowTypeReducer,
  transactionType: transactionTypeReducer,
  manager: managerReducer,
  initialPassword: initialPasswordReducer,
  registCompany: registCompanyReducer,
  managerState: managerStateSlice,
  department: departmentreducer,
  address: addressReducer,
  signUp: signUpReducer,
  company: companyReducer,
  auth: authReducer,
  example: exampleReducer,
})

export default rootReducer
