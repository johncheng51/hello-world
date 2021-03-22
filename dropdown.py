import dash
import dash_core_components as dcc
import dash_html_components as html
import dash_bootstrap_components as dbc
import dash_daq as daq

app = app = dash.Dash(external_stylesheets=[dbc.themes.BOOTSTRAP])

mainList=[str(i) for i in list(range(1,100))]
options=[]
for i in mainList:
  options.append({'label': " "+i,'value': i })
style={'overflow-y':'auto',
       'height':300,
       'padding-left': 5,
       'border-color': 'red',
       'border-width': 'thin'}
def getStyle(per):
 return {'width':str(per)+'%','display':"inline-block"}

def getAllClear():
    return html.Div([
    daq.ToggleSwitch(
        size=20,
        id='allClear',
        value=True,
        style=getStyle(20)),
    html.Div(
        id='allClearLabel',
        style=getStyle(80))])

app.layout = html.Div(
     children=dbc.DropdownMenu(
           children=[
              getAllClear(),
              dcc.Checklist(
                id="firstMenu",
                options=options,
                value=[],
                labelStyle={'display': 'block'},
                style=style)],
         label="First Menu"))


@app.callback(
    dash.dependencies.Output('firstMenu', 'value'),
    [dash.dependencies.Input('allClear', 'value')]
)
def checkList(name):
    if name: return mainList
    else: return [mainList[0]]

@app.callback(
    dash.dependencies.Output('allClearLabel', 'children'),
    [dash.dependencies.Input('allClear', 'value')]
)
def checkList(name):
    if name: return "Clear All"
    else: return "Select All"



if __name__ == '__main__':
    app.run_server()