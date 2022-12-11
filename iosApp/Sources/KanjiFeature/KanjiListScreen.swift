//
//  File.swift
//  
//
//  Created by lease-emp-mac-suraj-sau on 2022/12/11.
//

import SwiftUI
import shared
import Utils

public struct KanjiListScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    @ObservedObject private var nativeVM = NativeViewModel(viewModel: KanjiListViewModel.init())
    
    private let query: KanjiQuery

    public init(query: KanjiQuery) {
        self.query = query
    }
    
    public var body: some View {
        ZStack {
            if let state = self.dispatcher.state {
                VStack {
                    ScrollView {
                        LazyVGrid(columns: [GridItem(.adaptive(minimum: 72), spacing: 24)], spacing: 16) {
                            ForEach(state.items, id: \.self) { item in
                                KanjiGradeItem(grade: item.value)
                            }
                        }
                        .padding()
                    }
                    
                    Spacer()
                }
            }
        }
        .onAppear {
            nativeVM.viewModel.doInitWith(query: query)
        }
    }
}
