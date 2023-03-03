
import React, {useEffect, useState, useContext} from 'react';
import axios from 'axios';
import { Container, Button, ButtonGroup, Input, TextField, Typography } from '@mui/material'
import { Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';
import '@fontsource/roboto/700.css';
import ContactSupportOutlinedIcon from '@mui/icons-material/ContactSupportOutlined';
import Loading from './Loading';
import './App.css'

const ModContext = React.createContext();
function ModProvider({ children }) {
  const modState = useState([,]);
  return <ModContext.Provider value={modState}>
         {children}
        </ModContext.Provider>;
}
function useModState(putIndex) {
      const value = useContext(ModContext);
      if (value === undefined) throw new Error('error');
        const index = putIndex;
        return [ value[0][index],
          (newValue) => {
            const newArray = [...value[0]];
            newArray[index] = newValue;
            value[1](newArray);
          },];
}

    function CustomDial(props){
        const [mod, setMod] = useModState(1);
        return <Dialog open={mod}>
        <DialogTitle>도움말</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    <Typography variant='h6' sx={{color:'#FF0000'}}>#우선 알아야할 사항</Typography>
                    - Gpt ai는 한글 질문을 받을 경우 답변이 다소 느릴 수 있습니다.<br />
                    그렇기 때문에 한글로 질문하실 때 불가피한 경우(코드에 한글 포함, 번역이 안되는 한글 질문)
                    가 아니라면 1번 과정을 진행해주세요.<br />
                    - 답변의 정확성을 위해 되도록 프로그래밍 관련 질문을 해주세요.<br />
                    - openai의 Gpt 서버에 이상이 있거나 ai가 답변하지 못할 질문을 할 경우 긴 로딩이 발생하며
                    15초가 지나도 gpt api의 응답이 없을 경우 질문이 취소됩니다.<br /><br />

                    1. 번역이 가능한 한글 질문을 하실 예정이시라면 아래 그림처럼 질문을 작성한 뒤
                    번역 버튼을 눌러주세요. 질문은 100자까지 허용합니다.<br />
                    <img src={require("./assets/pictures/번역.png")} /><br /><br />
                    2. 궁금하신 질문이 떠오르시지 않는다면 아래 추천 질문 버튼을 통해 질문 문장을
                    완성하실 수 있습니다. 먼저 아래처럼 카테고리를 선택하세요.
                    <img src={require("./assets/pictures/그림1.png")} /><br />
                    카테고리를 선택하면 아래처럼 상세 질문 버튼 리스트가 출력됩니다.
                    <img src={require("./assets/pictures/그림3.png")} /><br />
                    상세 질문 버튼 중 하나를 클릭하시면 다음 탭에서 질문이 자동 완성됩니다.<br /><br />
                    3. 앞서 번역했거나 추천된 질문이 아래처럼 출력되거나<br />
                    직접 ai에게할 질문을 입력하신 뒤 버튼을 눌러주세요.<br />
                    <img src={require("./assets/pictures/그림4.png")} /><br /><br />
                    4. 버튼을 누르면 아래 그림처럼 로딩 스피너가 나오니 답변이 출력될 때까지 기다려주세요.
                    요청 중복 방지를 위해 질문은 15초에 한 번으로 제한합니다.
                    혹시 로딩 스피너가 사라지지 않는다면 정상 요청을 한 번 다시 해주세요.<br />
                    <img src={require("./assets/pictures/그림5.png")} /><br /><br />
                    5. 답변이 출력되었을 때 한글로 번역이 필요한 경우 번역 버튼을 눌러주세요.<br />
                    <img src={require("./assets/pictures/그림6.png")} /><br /><br />
                    6. 모든 과정에 대한 도움말이 끝났습니다. 닫기 버튼을 눌러 도움말을 종료해주세요. 도움말을 다시 보시려면 메인 화면 상단 버튼을 눌러주세요.<br />

                </DialogContentText>
                <DialogActions>
                    <Button variant='contained' onClick={() => setMod(false)}>닫기</Button>
                </DialogActions>
            </DialogContent>
    </Dialog>
    }

    function CustomQuestionDial(props){
            const [mod2, setMod2] = useModState(2);
            return <Dialog open={mod2}>
            <DialogTitle>질문 예시</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                    # 가급적 프로그래밍 관련 질문만 해주세요. 다른 질문은 직접 gpt ai를 사용하시길 추천합니다.
                    또한 코드의 예시를 그대로 사용할 경우. 저작권에 대한 불이익을 받을 수 있습니다.<br />
                    <br />1. 단순 프로그래밍 용어 질문<br />
                    <img src={require("./assets/pictures/질문1.png")} /><br /><br />
                    2. 에러 발생시 에러명&코드 질문<br />
                    <img src={require("./assets/pictures/질문2.png")} /><br /><br />
                    3. 특정 기능을 구현하는 코드 예시 질문<br />
                    <img src={require("./assets/pictures/질문3.png")} />
                    </DialogContentText>
                    <DialogActions>
                        <Button variant='contained' onClick={() => setMod2(false)}>닫기</Button>
                    </DialogActions>
                </DialogContent>
        </Dialog>
        }

    function DialButton(props){
        const [mod, setMod] = useModState(1);
        const [mod2, setMod2] = useModState(2);
        return <>
        <Button variant='outlined' onClick={() => setMod(true)}>도움말 열기</Button>
        <Button variant='contained' onClick={() => setMod2(true)}>질문 예시 보기</Button>
        </>
    }

const FormContext = React.createContext();
function FormProvider({ children }) {
      const formState = useState([, , ,]);
      return <FormContext.Provider value={formState}>
             {children}
            </FormContext.Provider>;
    }

function useFormState(putIndex) {
      const value = useContext(FormContext);
      if (value === undefined) throw new Error('error');
        const index = putIndex;
        return [ value[0][index],
          (newValue) => {
            const newArray = [...value[0]];
            newArray[index] = newValue;
            value[1](newArray);
          },];
}

function Box() {
    const [resultA, setResultA] = useFormState(2);
    console.log("box 렌더링");
    return <>
        <Container sx={{ m:1 , border: 1, padding: 2, borderColor: 'divider', backgroundColor: 'rgba(240, 255, 255, 0.8)'}}>
            <h3>번역이 필요할 경우 질문 입력</h3> <Form></Form>
            <h3>추천 질문</h3>
            <ButtonForm></ButtonForm>
        </Container>
        <Container sx={{ m:1, border: 1, padding: 2, borderColor: 'divider', backgroundColor: 'rgba(240, 255, 255, 0.8)' }}>
            <h3>앞에서 번역, 추천되었거나 혹은 직접 입력한 질문 제출</h3>  <TransForm></TransForm>
        </Container>
        <Container sx={{ m:1, border: 1, padding: 2, borderColor: 'divider', backgroundColor: 'rgba(240, 255, 255, 0.8)' }}>
            <h3>Gpt 답변 출력 및 번역 가능</h3>  <ResultForm></ResultForm>
            <h3>Gpt 번역된 답변</h3> <TransResultForm></TransResultForm>
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
                    .then(response => {
                    console.log(response);
                    setTransQ(JSON.stringify(response.data.message.result.translatedText).replace(/"/gi, ""));
                    })
                    .catch(error => {console.log(error);});
            }}>
            <p><Input sx={{width:300}} inputProps={{maxLength:100}} required type="text" name="originQ" placeholder='한글로 질문을 입력해주세요' value={bindingQ||""} onChange=
            {event => {setBindingQ(event.target.value)}} /></p>
            <p><Button variant="outlined" type="submit">번역</Button></p>
        </form>
}

function ButtonForm(props) {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);
    const [qArr, setQArr] = useState([]);
    const [q2Arr, setQ2Arr] = useState([]);
    const [lCategory, SetLCategory] = useState('');

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
                        axios.post('/api/requestRQ2',
                                        {category: `${qArr[i]}`})
                                    .then(response => {
                                        console.log(response);
                                        setQ2Arr(response.data);
                                    })
                                    .catch(error =>{console.log(error)})
                                    .finally(() => console.log("post 통신 성공"));
                        setTransQ(`What is the ${qArr[i]}?`);
                        SetLCategory(qArr[i]);
                        }}>{qArr[i]}</Button>);
                    }
        return result;
    }
        const grouping2 = () => {
            const result = [];
            for (let i=0;i<q2Arr.length;i++) {
                result.push(<Button variant="outlined" key={q2Arr[i]} value={q2Arr[i]} onClick={(event) => {
                            event.preventDefault();
                            setTransQ(`What is the ${q2Arr[i]} in ${lCategory}?`);
                            }}>{q2Arr[i]}</Button>);
                            
                }
            return result;    
    }
    return <>
        <p>카테고리</p>
         <ButtonGroup>{grouping()}</ButtonGroup><br /><br />
         <p>상세 질문</p>
         <ButtonGroup>{grouping2()}</ButtonGroup>
    </>
}

function TransForm(props) {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    console.log("transform 렌더링");
    return <>
    <form onSubmit={event => {
            setError('');
            setLoading(true);
            event.preventDefault();
            const LocalTransQ = event.target.transQ.value; 
            axios.post('/api/requestQuestion',
                            {questionContent: `${LocalTransQ}`})
                .then(response => {
                if (response.data.object === 'text_completion') {
                    setResultA(JSON.stringify(response.data.choices[0].text).slice(5,-1).replace(/\\n/gi,'\n').replace(/\\"/g,'\"'));
                } else {
                    setError("서버 동기화를 위해 요청은 10초마다 보낼 수 있습니다. 이 메세지는 정상 요청 이후 답변이 출력되면 사라집니다.");
                    window.alert("서버 동기화를 위해 요청은 10초마다 보낼 수 있습니다. 혹은 다른 사용자와 요청이 겹쳤을 수 있습니다."
                    )
               }})
                .catch(error => {console.log(error);
                window.alert("gpt서버 이상이나 잘못된 질문으로 인해 15초가 경과하여 질문이 취소됩니다.")})
                .finally(() => {console.log("post 통신 성공")
                                setLoading(false);});
            }}>
            <p><Input sx={{width:300}} inputProps={{maxLength:30000}} required type="text" name="transQ" placeholder='영어로 직접 입력 가능' value={transQ||''} onChange={
                    event => {setTransQ(event.target.value);}} /></p>
            <p><Button variant='outlined' type="submit">ai에게 질문</Button></p>
            <p id="a">{error}</p>
    </form>
    <p>{loading && <Loading />}</p>
    </>
}

function ResultForm() {
    const [bindingQ, setBindingQ] = useFormState(0);
    const [transQ, setTransQ] = useFormState(1);
    const [resultA, setResultA] = useFormState(2);
    const [transA, setTransA] = useFormState(3);


    console.log("resultform 렌더링");
    return <>
    <form onSubmit={event => {
        event.preventDefault();
        const result = event.target.resultA.value;
        axios.post('/api/requestTransEK',
                    {sentence: `${result}`})
                  .then(response => {
                    console.log(response);
                    setTransA(JSON.stringify(response.data.message.result.translatedText).replace(/"/gi, ""));
                
                })
                  .catch(error => console.log(error))
                  .finally(() => console.log("post 통신 성공"));
         }

  }>
    <TextField name="resultA" inputProps={{maxLength:30000}} fullWidth multiline value={resultA||''} onChange={event => {setResultA(event.target.value);}} />
    <p><Button variant="outlined" type="submit">번역</Button></p>
  </form>
  </>
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
            <CustomDial></CustomDial>
            <CustomQuestionDial></CustomQuestionDial><br/>
                <h1>gpt api를 이용한 프로그래밍 질문 웹서비스 <ContactSupportOutlinedIcon sx={{ fontSize:50, color:'#FFFFFF' }} /></h1>
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
