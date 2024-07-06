import { useState } from 'react'
import ManagerAutocomplete from '../autocomplete/ManagerAutocomplete'
import InputName2 from '../textfield/InputName2'
import SaveToolbar from '../toolbar/SaveToolbar'

const { Modal, Stack, Grid, Button } = require('@mui/material')

const AddDepartmentModal = ({ open, handleClose, data, onClick }) => {
  const initialData = {
    departmentName: '',
    departmentPresidentId: null,
  }

  const [departmentData, setDepartmentData] = useState(initialData)

  const handleInputChange = (field, value) => {
    setDepartmentData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSave = async () => {
    console.log(data.departmentId)
    console.log(departmentData)
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
          value={departmentData.departmentPresidentId} // Add this line
          onChange={(value) => {
            handleInputChange('departmentPresidentId', value)
          }}
          label="부서장"
        />
      </Stack>
    </Modal>
  )
}

AddDepartmentModal.displayName = 'AddDepartmentModal'

export default AddDepartmentModal
