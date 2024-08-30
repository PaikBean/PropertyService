import { combineReducers } from 'redux'
import exampleReducer from './slices/exampleSlice'
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
import propertyTypeReducer from './slices/propertyTypeSlice'
import clientReducer from './slices/clientSlice'
import genderReducer from './slices/genderSlice'
import departmentReducer2 from './slices/departmentSlice2'

const rootReducer = combineReducers({
  departmentList: departmentReducer2,
  genderList: genderReducer,
  clientList: clientReducer,
  propertyType: propertyTypeReducer,
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
  example: exampleReducer,
})

export default rootReducer
