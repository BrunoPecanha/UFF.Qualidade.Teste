<%@page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Financeiro - UFF - Extrato</title>

    <!-- Fontes personalizados para o template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Estilos personalizados para o template -->
    <link href="css/financeiro.css" rel="stylesheet">

    <!-- Estilos personalizados para esta página -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">    
</head>


<body id="page-top">

    <!-- Container principal da página -->
    <div id="wrapper">

       <!-- Barra de menu lateral -->      
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Barra lateral - Logo Uff -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="home.jsp">
                <div class="sidebar-brand-icon">
                    <img src="img/logouffbranco.png" alt="Logo UFF" width="50" height="50">
                </div>
            </a>

            <!-- Divisor -->
            <hr class="sidebar-divider my-0">

            <!-- Item em negrito - Painel de controle -->
            <li class="nav-item active">
                <a class="nav-link" href="home.jsp">
                    <i class="fas fa-tasks"></i>
                    <span>Painel de Controle</span></a>
            </li>

            <!-- Divisor -->
            <hr class="sidebar-divider">

        <!-- Menu Admin -->
        <div id="gerenciamentoadmin" style="${adm}">
        <div class="sidebar-heading">
            Área Admin
        </div>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/CategoriaController?action=listcateogoria">
                    <i class="fas fa-sitemap"></i>
                    <span>Categorias</span></a>
            </li>

            <!-- Item de navegação - Funcionalidades do menu colapsado -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Gerenciar Acessos</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Gerenciamentos</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/UsuarioController?action=listusuario">Usuários</a>      
                       <a class="collapse-item" href="${pageContext.request.contextPath}/AdminController?action=listadmin">Administradores</a>                                
                    </div>
                </div>
            </li>
            <!-- Divisor -->
            <hr class="sidebar-divider">
        </div>
                    
            <!-- Cabeçalho -->
            <div id="menuusuario" style="${usu}">
                <div class="sidebar-heading">               
                    <span>Gerenciar Conta</span>
                </div>            

                 <!-- Nav Item - Conta corrente -->
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ContaController?action=listconta&id=${sessionScope.idUsuarioLogado}&session=${sessionScope.usuarioLogado.nome}">
                        <i class="fas fa-university"></i>
                        <span>Conta Corrente</span></a>
                </li>

               <!-- Nav Item - Lançamentos -->
                <li class="nav-item">
                     <a class="nav-link" href="${pageContext.request.contextPath}/LancamentoController?action=listLancamento&id=${sessionScope.idUsuarioLogado}&session=${sessionScope.usuarioLogado.nome}">            
                        <i class="fas fa-file-invoice-dollar"></i>
                        <span>Lançamentos</span>
                     </a>
                </li>

                <!-- Divisor -->
                <hr class="sidebar-divider d-none d-md-block">
            </div> 

           <!-- Botao para recolher menu lateal -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- Fim da barra lateral -->

         <!-- Conteuúdo do container -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Conteúdo principal -->
            <div id="content">
                <!-- Barra superior -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <!-- Alternância da barra lateral (Barra superior) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>                    

                    <!-- Barra superior - Menu do usuário -->
                    <ul class="navbar-nav ml-auto">       
                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Item de navegação - Informações do usuário -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.usuarioLogado.nome}</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - Informações do usuário -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#perfilModal">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Perfil
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Sair
                                </a>
                            </div>
                        </li>
                    </ul>

                </nav>
                <!-- Fim da barra superior  -->

                <!-- Inicio do conteúdo da página -->
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-md-11">
                            <h1 class="h3 mb-2 text-gray-800">Lançamentos</h1>
                            <p class="mb-4">Área para o lançamentos de débitos e créditos.</p> 
                        </div>
                        <div class="col-md-1">
                            <a href="#" onclick="alternarOperacaoNovoOuEdicao('lancamentoModalLabel', 'novo', 'Lançamento')" data-toggle="modal" data-target="#lancamentoModal">
                                <i class="fas fa-4x fa-plus-circle"></i>                         
                            </a>
                            Adicionar
                        </div>                                        
                    </div>
                    <table class="table table-bordered table-hover" style="width: 100%;">
                        <thead>
                           <tr>     
                               <th  style="width: 33.33%">Total Débitos</th>                                                                                  
                               <th style="width: 33.33%">Total Créditos</th>
                               <th style="width: 10%">Saldo</th>                              
                           </tr>
                           </thead>  
                           <tbody>
                               <tr>               
                                   <td>R$ <c:out value="${debitos}"/></td>   
                                   <td>R$ <c:out value="${creditos}"/></td>  
                                   <td>R$ <c:out value="${total}"/></td>  
                               </tr>                                                 
                           </tbody>   
                     </table>
                        <div>                                   
                            <button id="processar" data-toggle="modal" data-target="#processarModal" class="btn btn-primary"> Processar Lançamentos
                            </button>
                         </div>
                        <br>
                    <!-- Tabela de lançamentos -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Gerenciar Conta > Lançamentos</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>     
                                            <th>Id</th>                                                                                  
                                            <th>Conta</th>
                                            <th>Categoria</th>
                                            <th>Valor R$</th>
                                            <th>Operação</th>
                                            <th>Data</th>
                                            <th>Descrição</th>  
                                            <th>Processado</th> 
                                            <th></th>                                                                           
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>       
                                            <th>Id</th>                                        
                                            <th>Conta</th>
                                            <th>Categoria</th>
                                            <th>Valor R$</th>
                                            <th>Operação</th>
                                            <th>Data</th>    
                                            <th>Descrição</th>  
                                            <th>Processado</th> 
                                            <th></th>                                                                          
                                        </tr>
                                    </tfoot>
                                   <tbody>                                       
                                        <c:forEach items="${lancamentos}" var="lancamento">
                                                <tr>                                                         
                                                    <td><c:out value="${lancamento.id}" /></td>   
                                                    <td><c:out value="${lancamento.contaId}" /></td>  
                                                    <td><c:out value="${lancamento.categoriaId}" /></td> 
                                                    <td>R$ <c:out value="${lancamento.valor}" /></td>                                                                                                    
                                                    <td><c:out value="${lancamento.operacao}" /></td> 
                                                    <td><c:out value="${lancamento.data}" /></td> 
                                                    <td><c:out value="${lancamento.descricao}" /></td> 
                                                    <td><c:out value="${lancamento.processado}" /></td> 
                                                    <td> 
                                                        <div class="text-center">
                                                                <a href="#" onclick="carregarDadosEdicao('lancamentoModalLabel', 'edit', 'Lançamento',  ['id_lancamento', 'valor', 'operacao', 'id_conta', 'id_categoria', 'descricao', 'processado'], [ '<c:out value='${lancamento.id}'/>', '<c:out value='${lancamento.valor}'/>', '<c:out value='${lancamento.operacao}'/>', '<c:out value='${lancamento.contaId}'/>', '<c:out value='${lancamento.categoriaId}'/>', '<c:out value='${lancamento.descricao}'/>', '<c:out value='${lancamento.processado}'/>'])" data-toggle="modal" data-target="#lancamentoModal">
                                                                    <i class="fas fa-1x fa-edit pr-1"></i>                       
                                                                </a>
                                                                <a href="#" data-toggle="modal" onclick="carregarDadosEdicao('lancamentoLabel', 'delete', 'Lançamento', ['id_exclusao'], [ '<c:out value='${lancamento.id}'/>'])"   data-target="#exclusaoModal">
                                                                                <i class="fas fa-1x fa-trash-alt"></i>                    
                                                                </a>
                                                        </div>                                                         
                                                    </td>                                                  						
                                                </tr>
                                        </c:forEach>                                                                         
                                    </tbody>
                                </table>
                                <br>                               
                            </div>
                        </div>
                    </div>
                </div>
               <!-- Fim da Table mockado para exemplo -->
            </div>
            <!-- Fim do conteúdo principal -->

            <!-- Footer -->
            <footer class="sticky-footer  bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; UFF  Universidade Federal Fluminense<br>Qualidade e Teste - 2022.2</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- Fim do container do conteúdo -->

    </div>
    <!-- Fim do Container principal da página -->

    <!--Botão para rolagem para o topo -->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Modal de Logout-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Sair</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Tem certeza que deseja sair?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                     <a class="btn btn-primary" href="${pageContext.request.contextPath}/AutenticacaoController?deslogar=sim">Sair</a>
                </div>
            </div>
        </div>
    </div>
    <!--Fim do Modal de Logout -->  
    
    <!-- Modal de criação de lançamento -->
    <div class="modal fade" id="lancamentoModal" tabindex="-1" role="dialog" aria-labelledby="lancamentoModalLabel"
    aria-hidden="true">
       <div class="modal-dialog" role="document">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="lancamentoModalLabel">Novo Lançamento</h5>
                   <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                       <span aria-hidden="true">×</span>
                   </button>
               </div>
               <!-- Corpo do modal -->
               <div class="modal-body">                    
                  <form neme="lancamento" class="row g-3 needs-validation" method="post" action="${pageContext.request.contextPath}/LancamentoController?action=salvar&id=${sessionScope.idUsuarioLogado}&session=${sessionScope.usuarioLogado.nome}">                 
                    <div class="col-md-3">                            
                        <label for="validationCustom01" class="form-label">Valor</label>
                        <input type="text" class="form-control" id="valor" name="valor" value="0,00" required>                        
                      </div>
                      <div class="col-md-2">
                        <label for="validationCustom02" class="form-label">Operação</label>
                        <div class="form-check"> 
                            <div>
                                <input value="D" class="form-check-input mr-3" type="radio" name="operacao" id="debito">
                                <label class="form-check-label" for="tipoOperacao">
                                Débito
                                </label>
                            </div>                         
                            <div>
                                <input value="C" class="form-check-input" type="radio" name="operacao" id="credito">
                                <label class="form-check-label" for="tipoOperacao">
                                Crédito
                                </label>
                            </div>                            
                        </div>          
                      </div>
                      <div class="col-md-3">
                        <label for="validationCustom04" class="form-label">Conta</label>
                        <select name="id_conta" id="id_conta">
                            <option disabled selected>Selecione</option>
                            <c:forEach  items="${contaslancamento}" var="conta">
                                <option value="${conta.id}">${conta.descricao}</option>
                            </c:forEach>
                        </select>                      
                      </div>  
                      <div class="col-md-1">
                        <label for="validationCustom04" class="form-label">Categoria</label>
                        <select name="id_categoria"  id="id_categoria">
                            <option disabled selected>Selecione</option>
                            <c:forEach  items="${categoriaslancamento}" var="categoria">
                                <option value="${categoria.id}">${categoria.descricao}</option>
                            </c:forEach>
                        </select>                          
                    </div>   
                      <div class="col-md-12">
                        <input  name="id_categoria" class="form-control" type="hidden" value="<c:out value="${categoria.id}"/>" id="id"> 
                        <input  name="id_conta" class="form-control" type="hidden" value="<c:out value="${conta.id}"/>" id="id"> 
                        <input  name="id_lancamento" class="form-control" type="hidden" value="<c:out value="${lancamento.id}"/>" id="id_lancamento">
                        <label for="validationCustom03" class="form-label">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao" required>                      
                      </div>  
                       <div class="modal-footer mt-2" style="padding-left: 60%;">
                            <button id="gravar" class="btn btn-primary" value="Submit" type="submit">Gravar</button>
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>                 
                        </div>                    
                   </form>             
               </div>                           
           </div>
       </div>
   </div>
   <!--Fim modal de criação de lançamentos -->

    <!-- Modal de informações de usuários -->
   <div class="modal fade" id="perfilModal" tabindex="-1" role="dialog" aria-labelledby="perfilModalLabel"
   aria-hidden="true">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="usuarioModalLabel">Informações do usuário logado</h5>
                  <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">×</span>
                  </button>
              </div>
              <!-- Corpo do modal -->
              <div class="modal-body">                    
                  <form class="row g-3 needs-validation" novalidate>
                      <div class="col-md-6">
                        <label for="nomeValicacao" class="form-label">Nome</label>
                        <label class="text-primary" for="nomeValicacao">${sessionScope.usuarioLogado.nome}</label>                          
                      </div>
                      <div class="col-md-5">
                        <label class="form-label">CPF</label>
                        <label class="text-primary" class="form-label">${sessionScope.cpf}</label>
                      </div>                     
                  </form>             
              </div>
              <div class="modal-footer">
                  <button class="btn btn-primary" type="submit" data-dismiss="modal">Ok</button>                               
              </div>                
          </div>
      </div>
  </div>
  <!--Fim modal de informações do usuario logado -->

   
    <!-- Modal de exclusão -->
        <div class="modal fade" id="exclusaoModal" tabindex="-1" role="dialog" aria-labelledby="exclusaoModalLabel"
        aria-hidden="true">
            <div class="modal-dialog" role="document">

                    <div class="modal-content">    
                        <form method="post" action="${pageContext.request.contextPath}/LancamentoController?action=delete&id=${sessionScope.idUsuarioLogado}">                             
                            <div class="modal-header">
                                <h5 class="modal-title" id="exclusaoModalLabel">Excluir Lançamento</h5>
                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">Tem certeza que deseja excluir?</div>
                            <input  name="id_exclusao" class="form-control" type="hidden"  value="<c:out value="${lancamento.id}"/>" id="id_exclusao">   
                            <div class="modal-footer">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                <button class="btn btn-primary"  value="Submit" type="submit">Confirmar</button>
                            </div>
                        </form>
                    </div>
            </div>
        </div>
        <!--Fim do Modal de exclusão --> 
        
        
        <!-- Modal de confirmação processamento -->
        <div class="modal fade" id="processarModal" tabindex="-1" role="dialog" aria-labelledby="processarModalLabel"
        aria-hidden="true">
            <div class="modal-dialog" role="document">
                    <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="processarModalLabel">Processar Pendentes</h5>
                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">Confirma processar os lançamentos pendentes?</div>
                            <div class="modal-footer">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                <button class="btn btn-primary"  onClick="document.location.reload(true)">Confirmar</button>
                            </div>
                    </div>
            </div>
        </div>
        <!--Fim do Modal de confirmação processamento  --> 

    <!-- Javascript principal do bootstrap -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Javascript principal do plugin -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Script personalizado para todas as páginas -->
    <script src="js/financeiro.js"></script>    

    <!-- Plugins externos -->
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Script do plugin externo -->
    <script src="js/datatables.js"></script>

    <!-- Script personalizado para todas as páginas -->
    <script src="js/lancamentos.js"></script>
</body>


 <script>
        $(document).ready(function () {
            $('#processar').click(function () {
                $.post('${pageContext.request.contextPath}/LancamentoController?action=processar&id=${sessionScope.idUsuarioLogado}')
            });
        });
 </script>

</html>


