package com.unina.oobd2324gr18.control;

import com.unina.oobd2324gr18.DTO.OperatorDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;

/**
 * Gestisce la sessione corrente dell'operatore.
 *
 * @author DavideGarigiulo
 */
public final class Session {
  /**
   * L'operatore attualmente loggato.
   */
  private static OperatorDTO operatorLogged;

  /**
   * L'ordine selezionato nella sessione corrente.
   */
  private static OrderDTO selectedOrder;

  /**
   * Costruttore privato per prevenire l'istanziazione.
   *
   * @throws MethodNotImplemented sempre.
   */
  private Session() {
    throw new MethodNotImplemented();
  }

  /**
   * Effettua il login di un operatore nella sessione.
   *
   * @param operator L'operatore che effettua il login.
   */
  static void loginOperator(final OperatorDTO operator) {
    Session.operatorLogged = operator;
  }

  /**
   * Seleziona un ordine nella sessione corrente.
   *
   * @param order L'ordine da selezionare.
   */
  static void selectOrder(final OrderDTO order) {
    Session.selectedOrder = order;
  }

  /**
   * Restituisce l'operatore loggato nella sessione corrente.
   *
   * @return L'operatore loggato.
   */
  public static OperatorDTO getOperatorLogged() {
    return operatorLogged;
  }

  /**
   * Restituisce l'ordine selezionato nella sessione corrente.
   *
   * @return L'ordine selezionato.
   */
  public static OrderDTO getSelectedOrder() {
    return selectedOrder;
  }

  /**
   * Effettua il logout dalla sessione, resettando l'operatore loggato e l'ordine selezionato.
   */
  public static void logout() {
    operatorLogged = null;
    selectedOrder = null;
  }
}
