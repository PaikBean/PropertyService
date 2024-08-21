// React, Next
import { useState } from 'react'

// Materials
const { Modal, Stack } = require('@mui/material')

// Custom Components
import ManagerAutocomplete from '../autocomplete/ManagerAutocomplete'
import InputName2 from '../textfield/InputName2'
import SaveToolbar from '../toolbar/SaveToolbar'
import { fetchCreateDepartment } from '@/pages/manageCompany/api/fetchCreateDepartment'

// Utils

const AddDepartmentModal = ({ open, handleClose, companyId , setIsAddDepartmentModalOpen}) => {
  const initialData = {
    companyId: companyId,
    departmentCode: new Date(),
    departmentName: '',
    managerId: null,
  }

  const [departmentData, setDepartmentData] = useState(initialData)

  const handleInputChange = (field, value) => {
    setDepartmentData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  
  const handleSave = async () => {
    console.log(departmentData)
    try{
      const response = await fetchCreateDepartment(departmentData)
      console.log(response)
      if(response.responseCode === "SUCCESS"){
        setIsAddDepartmentModalOpen(false)
      } else{
        throw new Error(response.message || 'Error!')
      }
    } catch(error){
      alert(error)
    }

    return
  }

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="data-modal-title"
      aria-describedby="data-modal-content"
    >
      <Stack
        spacing={3}
        sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          width: 800,
          bgcolor: 'background.paper',
          boxShadow: 24,
          p: 5,
        }}
      >
        <SaveToolbar text={'부서 등록'} onClick={handleSave}></SaveToolbar>
        <InputName2
          label="부서명"
          value={departmentData.departmentName}
          onChange={(e) => {
            handleInputChange('departmentName', e.target.value)
          }}
        />
        <ManagerAutocomplete
          value={departmentData.managerId} // Add this line
          onChange={(value) => {
            handleInputChange('managerId', value)
          }}
          label="부서장"
        />
      </Stack>
    </Modal>
  )
}

AddDepartmentModal.displayName = 'AddDepartmentModal'

export default AddDepartmentModal
