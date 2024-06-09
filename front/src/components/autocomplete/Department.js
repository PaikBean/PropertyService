import { fetchDepartmentList } from '@/store/slices/departmentSlice'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Autocomplete, TextField } from '@mui/material'

const Department = ({ onChange }) => {
  const dispatch = useDispatch()
  const { companyInfo } = useSelector((state) => state.company)
  const { options, status, error } = useSelector((state) => state.department)

  useEffect(() => {
    dispatch(fetchDepartmentList(companyInfo.companyCode))
  }, [companyInfo, dispatch])

  const handleAddressChange = (event, value) => {
    onChange(value ? value.departmentId : '')
  }
  return (
    <Autocomplete
      options={options || []}
      getOptionLabel={(options) => options.department || ''}
      onChange={handleAddressChange}
      renderInput={(params) => <TextField {...params} label="Department" />}
    />
  )
}

Department.displayName = 'Department'

export default Department
