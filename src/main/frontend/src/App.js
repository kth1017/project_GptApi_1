
import React, {useEffect, useState, useContext} from 'react';
import axios from 'axios';
import { Container, Grid, Button, ButtonGroup, Input, TextField, Typography } from '@mui/material'
import { Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';
import '@fontsource/roboto/700.css';
import QuestionAnswerIcon from '@mui/icons-material/QuestionAnswer';



const ModContext = React.createContext();
function ModProvider({ children }) {
  const modState = useState(true);
  return <ModContext.Provider value={modState}>
         {children}
        </ModContext.Provider>;
}
function useModState() {
  const value = useContext(ModContext);
  if (value === undefined) {
    throw new Error('error')

    }   return value;
}

    function CustomDial(props){
        const [mod, setMod] = useModState();
        const Mfalse = () => setMod(false);
        return <Dialog open={mod}>
        <DialogTitle>사용법</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    1. ai에게 할 질문이 한글이라면 번역을 위한 한글 질문을 입력해주시거나 추천 질문 버튼을 눌러주세요.<br />
                    2. 번역된 질문 또는 직접 입력한 영어 질문이 'ai에게 질문하기' 버튼을 누르시면 아래에 답변이 출력됩니다.
                </DialogContentText>
                <DialogActions>
                    <Button variant='contained' onClick={Mfalse}>닫기</Button>
                </DialogActions>
            </DialogContent>
    </Dialog>
    }

    function DialButton(props){
        const [mod, setMod] = useModState();
        const Mtrue = () => setMod(true)
        return <Button variant='contained' onClick={Mtrue}>도움말 다시열기</Button>
    }




const FormContext = React.createContext();
function FormProvider({ children }) {
      const formState = useState([, , , ,false]);
      return <FormContext.Provider value={formState}>
             {children}
            </FormContext.Provider>;
    }
function useFormState(putIndex) {
      const value = useContext(FormContext);
      if (value === undefined) {
        throw new Error('error')

        }
        const index = putIndex;
        return [ value[0][index],
          (newValue) => {
            const newArray = [...value[0]];
            newArray[index] = newValue;
            value[1](newArray);
          },
        ];
    }


function Box() {

    const [resultA, setResultA] = useFormState(2);
    console.log("box 렌더링");

    return <>

                <Container sx={{ border: 1, padding: 2, borderColor: 'divider' }}>
                    질문 입력 <Form></Form>
                    추천 질문 <br />
                    <ButtonGroup><ButtonForm></ButtonForm></ButtonGroup>
                </Container>
                <Container sx={{ border: 1, padding: 2, borderColor: 'divider' }}>
                    번역 <TransForm></TransForm>
                </Container>
                <Container sx={{ border: 1, padding: 2, borderColor: 'divider' }}>
                    답변 <ResultForm></ResultForm>
                    답변 번역 <TransResultForm></TransResultForm>
                </Container>

        </>
}


function Form(props) {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);
    console.log("form 렌더링");

    return <form onSubmit={event =>{
        event.preventDefault();
        const originQ = event.target.originQ.value;
        axios.post('/api/requestTransKE',
                    {sentence: `${originQ}`})
                  .then(function (response) {
                    console.log(response);
                    axios.get('/api/responseTransKE')
                    .then(response => {setTransQ(JSON.stringify(response.data.message.result.translatedText).replace(/"/gi, ""));
                                        })
                    .catch(error => console.log(error))
                  })
                  .catch(function (error) {
                    console.log(error);});



        }}>
                <p><Input type="text" name="originQ" placeholder='한글로 질문을 입력해주세요' value={bindingQ||""} onChange=
                {event => {setBindingQ(event.target.value)}} /></p>
                <p><Button variant="outlined" type="submit">번역</Button></p>
            </form>
}

function ButtonForm(props) {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);
    const [qArr, setQArr] = useState([]);

    console.log("buttonform 렌더링");

    useEffect(() => {
        axios.get('/api/requestRQ')
        .then(response => setQArr(response.data))
        .catch(error => console.log(error))
    }, []);

    const grouping = () => {
        const result = [];
        for (let i=0;i<qArr.length;i++) {
            result.push(<Button variant="outlined" key={qArr[i]} value={qArr[i]} onClick={(event) => {
                        event.preventDefault();
                        axios.post('/api/requestTransKE',
                            {sentence: `What is the ${qArr[i]}?`, originA: `What is the ${qArr[i]}?`})
                            .then(function (response) {
                                console.log(response);
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                        setTransQ(`What is the ${qArr[i]}?`);
                        }}>{qArr[i]}</Button>);

            }
        return result;
    }
    return grouping()


}

function TransForm(props) {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);

    console.log("transform 렌더링");
    return <form onSubmit={event => {
        const LocalTransQ = event.target.transQ.value;
            event.preventDefault();

            axios.post('/request',
                {originQ: `${LocalTransQ}`})
                .then(function (response) {

                    axios.get('/api/sendQ')
                        .then(response => {
                        setResultA(JSON.stringify(response.data.choices[0].text).slice(5,-1).replace(/\\n/gi,'\n'))
                         })
                        .catch(error => console.log(error));
                        setTransQ(LocalTransQ);
                })
                .catch(error => {console.log(error)});
            }}>
            <p><Input required type="text" name="transQ" placeholder='영어로 직접 입력 가능' value={transQ||''} onChange={
                    event => {setTransQ(event.target.value);}} /></p>
            <p><Button variant='outlined' type="submit">ai에게 질문</Button></p>
    </form>
}

function ResultForm() {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);
    const [transA, setTransA] = useFormState(3);

    console.log("resultform 렌더링");
    return <form onSubmit={event => {
        event.preventDefault();
        const result = event.target.resultA.value;
        axios.post('/request',
                    {originQ: `${result}`})
                  .then(function (response) {
                    console.log(response);
                    axios.get('/api/transQ')
                    .then(response =>
                        setTransA(JSON.stringify(response.data.message.result.translatedText).replace(/"/gi, "")))
                    .catch(error => console.log(error));
                  })
                  .catch(function (error) {
                    console.log(error);})
         }

  }>
    <Input type="text" name="resultA" fullWidth multiline value={resultA||''} onChange={event =>
         {setResultA(event.target.value);
         }}></Input>
    <p><Button variant="outlined" type="submit">번역</Button></p>
  </form>
}

function TransResultForm() {
    const [transA, setTransA] = useFormState(3);
    return <TextField fullWidth multiline value={transA||''} onChange={event => {
        setTransA(event.target.value)
}}></TextField>

}


function App() {

  return (
        <>

            <Container>
                <ModProvider>
                <CustomDial></CustomDial><br/>
                    <QuestionAnswerIcon></QuestionAnswerIcon>
                    <Typography variant="h4" component="h2">gpt api를 이용한 프로그래밍 질문 웹서비스</Typography>
                <br/>
                <Container>
                    <ButtonGroup>
                        <DialButton></DialButton>
                    </ButtonGroup><br/><br/>
                </Container>
                <FormProvider><Box></Box></FormProvider>
                </ModProvider>
            </Container>
        </>
        );
}

export default App;