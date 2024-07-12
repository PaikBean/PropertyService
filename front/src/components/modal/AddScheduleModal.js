import dayjs from 'dayjs'

// React, Next
import { useState } from 'react'

// Materials
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'

// Custom Components
import { Modal, Stack } from '@mui/material'
import RemarkTextField from '../textfield/RemarkTextField'
import ScheduleType from '../autocomplete/scheduleType'
import Priority from '../autocomplete/Priority'
import BasicDatePicker from '@/components/datepicker/BasicDatePicker'
import ManagerAutocomplete from '../autocomplete/ManagerAutocomplete'
import SaveToolbar from '../toolbar/SaveToolbar'

// Utils

import { fetchRegistSchedule } from '@/pages/clientsLedger/api/fetchRegistSchedule'

const AddScheduleModal = ({ open, handleClose, data, onClick }) => {
  const initialData = {
    managerId: null,
    clientId: data,
    scheduleTypeId: null,
    sheduleDate: '',
    priority: '',
    remark: '',
  }

  const [scheduleData, setScheduleData] = useState(initialData)

  const handleInputChange = (field, value) => {
    setScheduleData((prev) => ({
      ...prev,
      [field]: value,
    }))
  }
  const handleSave = async () => {
    console.log(scheduleData)
    try {
      const result = await fetchRegistSchedule(scheduleData)
      setScheduleData(initialData)
      handleClose()
    } catch (error) {
      alert(error.message)
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
        <SaveToolbar text={'일정 추가'} onClick={handleSave}></SaveToolbar>
        <ManagerAutocomplete
          value={scheduleData.managerId}
          onChange={(value) => {
            handleInputChange('managerId', value)
          }}
        />
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <BasicDatePicker
            label="일자"
            value={dayjs(scheduleData.sheduleDate)}
            onChange={(value) => {
              handleInputChange('sheduleDate', value.format('YYYYMMDD'))
            }}
          />
        </LocalizationProvider>
        <ScheduleType
          value={scheduleData.scheduleTypeId}
          onChange={(value) => {
            handleInputChange('scheduleTypeId', value)
          }}
        />
        <Priority
          value={scheduleData.priority}
          onChange={(value) => {
            handleInputChange('priority', value)
          }}
        />
        <RemarkTextField
          value={scheduleData.remark}
          onChange={(e) => {
            handleInputChange('remark', e.target.value)
          }}
          width={725}
          rows={2}
        />
      </Stack>
    </Modal>
  )
}

AddScheduleModal.displayName = 'AddScheduleModal'

export default AddScheduleModal
